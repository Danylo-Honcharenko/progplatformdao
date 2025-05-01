package dao;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.ua.fkrkm.proglatformdao.dao.TestDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TestDaoImpl;
import org.ua.fkrkm.proglatformdao.entityMongo.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImplTest {

    private TestDaoI testDao;
    private MongodExecutable mongodExecutable;

    @BeforeEach
    public void setup() throws Exception {
        ImmutableMongodConfig mongodConfig = MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net("localhost", 27017, true))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        this.mongodExecutable = starter.prepare(mongodConfig);
        this.mongodExecutable.start();
        this.testDao = new TestDaoImpl(new SimpleMongoClientDatabaseFactory("mongodb://localhost:27017/test"));
    }

    @AfterEach
    public void clear() {
        mongodExecutable.stop();
    }

    @Test
    public void createTest() {
        Question question = Question.builder()
                .question("Test 1")
                .correctAnswer("Test 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build();

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        org.ua.fkrkm.proglatformdao.entityMongo.Test test = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test")
                .topicId(1)
                .questions(questions)
                .build();

        String uuid = testDao.create(test);
        assertNotNull(uuid);
    }

    @Test
    @Disabled
    public void updateTest() {

    }

    @Test
    public void getAllTest() {
        Question question = Question.builder()
                .question("Test 1")
                .correctAnswer("Test 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build();

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        org.ua.fkrkm.proglatformdao.entityMongo.Test test = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test")
                .topicId(1)
                .questions(questions)
                .build();

        testDao.create(test);
        List<org.ua.fkrkm.proglatformdao.entityMongo.Test> tests = testDao.getAll();
        assertEquals(1, tests.size());
    }

    @Test
    public void getByUUIDTest() {
        Question question = Question.builder()
                .question("Test 1")
                .correctAnswer("Test 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build();

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        org.ua.fkrkm.proglatformdao.entityMongo.Test test = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test")
                .topicId(1)
                .questions(questions)
                .build();

        String uuid = testDao.create(test);
        org.ua.fkrkm.proglatformdao.entityMongo.Test testDaoById = testDao.getByUUID(uuid);
        assertNotNull(testDaoById);
    }

    @Test
    public void getByTopicIdTest() {

        Question question = Question.builder()
                .question("Test 1")
                .correctAnswer("Test 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build();

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        org.ua.fkrkm.proglatformdao.entityMongo.Test firstTest = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test")
                .topicId(1)
                .questions(questions)
                .build();

        org.ua.fkrkm.proglatformdao.entityMongo.Test secondTest = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test 2")
                .topicId(1)
                .questions(questions)
                .build();

        testDao.create(firstTest);
        testDao.create(secondTest);


        assertFalse(testDao.getByTopicId(1).isEmpty());
        assertEquals(2, testDao.getByTopicId(1).size());
    }

    @Test
    public void deleteTest() {
        Question question = Question.builder()
                .question("Test 1")
                .correctAnswer("Test 1")
                .options(List.of("Option 1", "Option 2", "Option 3"))
                .build();

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        org.ua.fkrkm.proglatformdao.entityMongo.Test test = org.ua.fkrkm.proglatformdao.entityMongo.Test.builder()
                .name("test")
                .topicId(1)
                .questions(questions)
                .build();

        String uuid = testDao.create(test);

        testDao.delete(uuid);
        org.ua.fkrkm.proglatformdao.entityMongo.Test testDaoById = testDao.getByUUID(uuid);
        assertNull(testDaoById);
    }
}
