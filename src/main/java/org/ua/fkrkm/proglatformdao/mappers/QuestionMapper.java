package org.ua.fkrkm.proglatformdao.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "Question"
 */
public class QuestionMapper implements RowMapper<Question> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs     рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return Question сутність
     * @throws SQLException виняток
     */
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Question.builder()
                .id(rs.getLong("id"))
                .testId(rs.getLong("test_id"))
                .question(rs.getString("question"))
                .correctAnswer(rs.getString("correct_answer"))
                .options(this.parseOptions(rs.getString("options")))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }

    /**
     * Перетворити JSON у список варіантів відповіді
     *
     * @param json JSON масив з бази даних
     * @return List<String> варіанти відповіді
     */
    private List<String> parseOptions(String json) throws SQLException {
        if (Objects.isNull(json)) return Collections.emptyList();
        try {
            // H2 може віддавати JSON, збережений як рядок, у вигляді екранованого рядка
            if (json.startsWith("\"")) {
                json = OBJECT_MAPPER.readValue(json, String.class);
            }
            return OBJECT_MAPPER.readValue(json, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new SQLException("Не вдалося розібрати варіанти відповіді: " + json, e);
        }
    }
}
