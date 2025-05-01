package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.ParentDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.CourseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Course;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ParentDaoImplTest {

    private final ParentDaoI<Course> parentDao = new CourseDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
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
                .id(2)
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
        Course course = parentDao.getById(1);
        assertEquals(1, course.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, parentDao.delete(3));
    }
}
