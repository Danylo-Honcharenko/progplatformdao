package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ua.fkrkm.proglatformdao.dao.TestDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TestDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Question;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImplTest {

    private TestDaoI testDao;

    @BeforeEach
    public void setUp() {
        this.testDao = new TestDaoImpl(Database.getDataSource());
    }

    private org.ua.fkrkm.proglatformdao.entity.Test newTest(String name, Long topicId) {
        List<Question> questions = new ArrayList<>();
        questions.add(Question.builder()
                .question("Test 1")
                .correctAnswer("Option 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build());
        questions.add(Question.builder()
                .question("Test 2")
                .correctAnswer("Option 2")
                .options(List.of("Option 1", "Option 2"))
                .build());

        return org.ua.fkrkm.proglatformdao.entity.Test.builder()
                .name(name)
                .topicId(topicId)
                .questions(questions)
                .created(new Date())
                .build();
    }

    @Test
    public void createTest() {
        int id = testDao.create(newTest("create", 1L));

        assertTrue(id > 0);

        org.ua.fkrkm.proglatformdao.entity.Test saved = testDao.getById((long) id).get(0);
        assertEquals("create", saved.getName());
        assertEquals(2, saved.getQuestions().size());
        assertEquals(List.of("Option 1", "Option 2", "Option 3"), saved.getQuestions().get(0).getOptions());
    }

    @Test
    public void updateTest() {
        long id = testDao.create(newTest("update", 1L));

        org.ua.fkrkm.proglatformdao.entity.Test updated = org.ua.fkrkm.proglatformdao.entity.Test.builder()
                .id(id)
                .name("updated")
                .topicId(2L)
                .questions(List.of(Question.builder()
                        .question("New question")
                        .correctAnswer("A")
                        .options(List.of("A", "B"))
                        .build()))
                .updated(new Date())
                .build();

        assertEquals(1, testDao.update(updated));

        org.ua.fkrkm.proglatformdao.entity.Test saved = testDao.getById(id).get(0);
        assertEquals("updated", saved.getName());
        assertEquals(2L, saved.getTopicId());
        assertEquals(1, saved.getQuestions().size());
        assertEquals("New question", saved.getQuestions().get(0).getQuestion());
    }

    @Test
    public void updateWithoutQuestionsKeepsQuestionsTest() {
        long id = testDao.create(newTest("keep", 1L));

        org.ua.fkrkm.proglatformdao.entity.Test updated = org.ua.fkrkm.proglatformdao.entity.Test.builder()
                .id(id)
                .name("keep updated")
                .topicId(1L)
                .updated(new Date())
                .build();

        assertEquals(1, testDao.update(updated));
        assertEquals(2, testDao.getById(id).get(0).getQuestions().size());
    }

    @Test
    public void getByIdTest() {
        List<org.ua.fkrkm.proglatformdao.entity.Test> tests = testDao.getById(1L);

        assertEquals(1, tests.size());
        assertEquals("Pointers basics", tests.get(0).getName());
        assertEquals(3, tests.get(0).getQuestions().size());
        assertEquals(List.of("&", "*", "->", "::"), tests.get(0).getQuestions().get(0).getOptions());
    }

    @Test
    public void getByIdNotFoundTest() {
        assertTrue(testDao.getById(-1L).isEmpty());
    }

    @Test
    public void getByTopicIdTest() {
        List<org.ua.fkrkm.proglatformdao.entity.Test> tests = testDao.getByTopicId(2L);

        assertTrue(tests.size() >= 2);
        tests.forEach(test -> {
            assertEquals(2L, test.getTopicId());
            assertFalse(test.getQuestions().isEmpty());
        });
    }

    @Test
    public void getAllTest() {
        List<org.ua.fkrkm.proglatformdao.entity.Test> tests = testDao.getAll();

        assertTrue(tests.size() >= 3);
        tests.forEach(test -> assertFalse(test.getQuestions().isEmpty()));
    }

    @Test
    public void deleteTest() {
        long id = testDao.create(newTest("delete", 1L));

        assertEquals(1, testDao.delete(id));
        assertTrue(testDao.getById(id).isEmpty());
    }
}
