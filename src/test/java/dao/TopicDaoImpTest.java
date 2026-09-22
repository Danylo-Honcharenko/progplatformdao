package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.TopicDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TopicDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Topic;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopicDaoImpTest {

    private TopicDaoI topicDao;

    @BeforeEach
    public void setUp() {
        this.topicDao = new TopicDaoImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        Topic topic = Topic.builder()
                .name("Test")
                .description("test")
                .moduleId(1L)
                .created(new Date())
                .build();

        assertTrue(topicDao.create(topic) > 0);
    }

    @Test
    public void updateTest() {
        Topic updated = Topic.builder()
                .id(1L)
                .name("Test 1")
                .description("test 2")
                .moduleId(1L)
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
        List<Topic> topic = topicDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(topic));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, topicDao.delete(3L));
    }

    @Test
    public void findAllTopicsByModuleIdTest() {
        List<Topic> topics = topicDao.findAllTopicsByModuleId(1L);
        assertFalse(topics.isEmpty());
    }

    @Test
    public void findAllTopicsByModuleIdListTest() {
        List<Topic> allTopicsByModuleIdList = topicDao.findAllTopicsByModuleIdList(List.of(1L, 2L));
        assertFalse(allTopicsByModuleIdList.isEmpty());
    }
}
