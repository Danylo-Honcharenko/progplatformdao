package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ua.fkrkm.proglatformdao.dao.CourseDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.CourseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourseDaoImplTest {

    private CourseDaoI courseDao;

    @BeforeEach
    public void setUp() {
        this.courseDao = new CourseDaoImpl(Database.getDataSource());
    }

    @Test
    public void addUserToCourseTest() {
        courseDao.addUserToCourse(1L, 2L);
    }

    @Test
    public void getCourseUsersIdByCourseIdTest() {
        List<Long> courseUsersId = courseDao.getCourseUsersIdByCourseId(1L);
        assertFalse(courseUsersId.isEmpty());
    }

    @Test
    public void getCoursesIdByUserIdTest() {
        List<Course> courses = courseDao.getCoursesIdByUserId(1L);
        assertFalse(courses.isEmpty());
    }
}
