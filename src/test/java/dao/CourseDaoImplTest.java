package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.CourseDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.CourseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Course;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CourseDaoImplTest {

    private final CourseDaoI courseDao = new CourseDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void addUserToCourseTest() {
        courseDao.addUserToCourse(1, 2);
    }

    @Test
    public void getCourseUsersIdByCourseIdTest() {
        List<Integer> courseUsersId = courseDao.getCourseUsersIdByCourseId(1);
        assertFalse(courseUsersId.isEmpty());
    }

    @Test
    public void getCoursesIdByUserIdTest() {
        List<Course> courses = courseDao.getCoursesIdByUserId(1);
        assertFalse(courses.isEmpty());
    }
}
