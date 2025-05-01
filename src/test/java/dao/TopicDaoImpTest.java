package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.TopicDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TopicDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Topic;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopicDaoImpTest {

    private final TopicDaoI topicDao = new TopicDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        Topic topic = Topic.builder()
                .name("Test")
                .description("test")
                .moduleId(1)
                .created(new Date())
                .build();

        assertTrue(topicDao.create(topic) > 0);
    }

    @Test
    public void updateTest() {
        Topic updated = Topic.builder()
                .id(1)
                .name("Test 1")
                .description("test 2")
                .moduleId(1)
                .updated(new Date())
                .build();

        assertEquals(1, topicDao.update(updated));
    }

    @Test
    public void getAllTest() {
        List<Topic> topics = topicDao.getAll();
        assertFalse(topics.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Topic topic = topicDao.getById(1);
        assertEquals(1, topic.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, topicDao.delete(3));
    }

    @Test
    public void findAllTopicsByModuleIdTest() {
        List<Topic> topics = topicDao.findAllTopicsByModuleId(1);
        assertFalse(topics.isEmpty());
    }
}
