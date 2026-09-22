package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.ParentDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.CourseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Course;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ParentDaoImplTest {

    private ParentDaoI<Course> parentDao;

    @BeforeEach
    public void setUp() {
        this.parentDao = new CourseDaoImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        Course course = Course.builder()
                .name("Test Course")
                .description("sdsdsds")
                .created(new Date())
                .build();

        assertTrue(parentDao.create(course) > 0);
    }

    @Test
    public void updateTest() {
        Course updated = Course.builder()
                .id(2L)
                .name("Test Course 2")
                .description("123456789")
                .updated(new Date())
                .build();

        assertEquals(1, parentDao.update(updated));
    }

    @Test
    public void getAllTest() {
        List<Course> courses = parentDao.getAll();
        assertFalse(courses.isEmpty());
    }

    @Test
    public void getByIdTest() {
        List<Course> course = parentDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(course));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, parentDao.delete(3L));
    }
}
