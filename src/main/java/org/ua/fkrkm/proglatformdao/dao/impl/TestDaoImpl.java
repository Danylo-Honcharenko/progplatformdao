package org.ua.fkrkm.proglatformdao.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.ua.fkrkm.proglatformdao.dao.TestDaoI;
import org.ua.fkrkm.proglatformdao.entity.Question;
import org.ua.fkrkm.proglatformdao.entity.Test;
import org.ua.fkrkm.proglatformdao.mappers.QuestionMapper;
import org.ua.fkrkm.proglatformdao.mappers.TestMapper;

import javax.sql.DataSource;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Клас для роботи з сутністю "Тест"
 */
public class TestDaoImpl extends ParentDaoImpl<Test> implements TestDaoI {

    private static final String QUESTION_TABLE = "question";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Тест та його питання зберігаються в одній транзакції
    private final TransactionTemplate transactionTemplate;

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public TestDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("test");
        setParam(":name, :topicId, :created");
        setValues("name, topic_id, created");
        setValuesForUpdate("name = :name, topic_id = :topicId, updated = :updated");
        setRowMapper(new TestMapper());
        this.transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Test entity) {
        return inTransaction(() -> {
            Long id = super.create(entity);
            insertQuestions(id, entity.getQuestions());
            return id;
        });
    }

    /**
     * {@inheritDoc}
     * Якщо питання не передані (null), вони залишаються без змін, інакше повністю замінюються
     */
    @Override
    public int update(Test entity) {
        return inTransaction(() -> {
            int updated = super.update(entity);
            if (updated > 0 && Objects.nonNull(entity.getQuestions())) {
                deleteQuestions(entity.getId());
                insertQuestions(entity.getId(), entity.getQuestions());
            }
            return updated;
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getById(Long id) {
        return fillQuestions(super.getById(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getAll() {
        return fillQuestions(super.getAll());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Test> getByTopicId(Long topicId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE topic_id = :topicId";
        List<Test> tests = this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("topicId", topicId), new TestMapper());
        return fillQuestions(tests);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Long id) {
        return inTransaction(() -> {
            deleteQuestions(id);
            return super.delete(id);
        });
    }

    /**
     * Завантажити питання для тестів одним запитом
     *
     * @param tests тести
     * @return List<Test> ті самі тести з заповненими питаннями
     */
    private List<Test> fillQuestions(List<Test> tests) {
        if (tests.isEmpty()) return tests;

        List<Long> testIds = tests.stream()
                .map(Test::getId)
                .toList();

        String sql = "SELECT * FROM " + QUESTION_TABLE + " WHERE test_id IN (:testIds) ORDER BY id";
        List<Question> questions = this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("testIds", testIds), new QuestionMapper());

        Map<Long, List<Question>> questionsByTest = questions.stream()
                .collect(Collectors.groupingBy(Question::getTestId));

        return tests.stream()
                .peek((test) -> test.setQuestions(questionsByTest.getOrDefault(test.getId(), Collections.emptyList())))
                .toList();
    }

    /**
     * Зберегти питання тесту
     *
     * @param testId    ID тесту
     * @param questions питання
     */
    private void insertQuestions(Long testId, List<Question> questions) {
        if (Objects.isNull(questions) || questions.isEmpty()) return;

        String sql = "INSERT INTO " + QUESTION_TABLE + " (test_id, question, correct_answer, options, created) "
                + "VALUES (:testId, :question, :correctAnswer, :options::jsonb, :created)";

        Date now = new Date();

        SqlParameterSource[] batch = questions.stream()
                .map(question -> new MapSqlParameterSource()
                        .addValue("testId", testId)
                        .addValue("question", question.getQuestion())
                        .addValue("correctAnswer", question.getCorrectAnswer())
                        .addValue("options", toJson(question.getOptions()))
                        .addValue("created", Objects.nonNull(question.getCreated()) ? question.getCreated() : now))
                .toArray(SqlParameterSource[]::new);

        this.namedParameterJdbcTemplate.batchUpdate(sql, batch);
    }

    /**
     * Видалити всі питання тесту
     *
     * @param testId ID тесту
     */
    private void deleteQuestions(Long testId) {
        String sql = "DELETE FROM " + QUESTION_TABLE + " WHERE test_id = :testId";
        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("testId", testId));
    }

    /**
     * Перетворити варіанти відповіді у JSON для збереження в колонці JSONB
     *
     * @param options варіанти відповіді (null вважається порожнім списком)
     * @return String JSON масив
     * @throws IllegalArgumentException якщо не вдалося серіалізувати варіанти
     */
    private String toJson(List<String> options) {
        try {
            return OBJECT_MAPPER.writeValueAsString(Objects.isNull(options) ? Collections.EMPTY_LIST : options);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Не вдалося перетворити варіанти відповіді у JSON", e);
        }
    }

    /**
     * Виконати дію в одній транзакції. Якщо дія кидає виняток, зміни відкочуються
     *
     * @param action дія, що виконується в транзакції
     * @param <T>    тип результату дії
     * @return T результат дії
     */
    private <T> T inTransaction(Supplier<T> action) {
        return transactionTemplate.execute(status -> action.get());
    }
}
