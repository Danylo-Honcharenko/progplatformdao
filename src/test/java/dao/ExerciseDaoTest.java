package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.ExerciseDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.ExerciseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Exercise;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseDaoTest {

    private final ExerciseDaoI exerciseDaoI = new ExerciseDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        Exercise exercise = Exercise.builder()
                .name("Exercise 2")
                .description("Exercise")
                .assessment(12)
                .topicId(2)
                .created(new Date())
                .build();

        assertTrue(exerciseDaoI.create(exercise) > 0);
    }

    @Test
    public void updateTest() {
        Exercise updated = Exercise.builder()
                .id(1)
                .name("Exercise 1")
                .description("Exercise")
                .assessment(11)
                .topicId(2)
                .updated(new Date())
                .build();

        assertEquals(1, exerciseDaoI.update(updated));
    }

    @Test
    public void getAllTest() {
        List<Exercise> exercises = exerciseDaoI.getAll();
        assertFalse(exercises.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Exercise exercise = exerciseDaoI.getById(1);
        assertEquals(1, exercise.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, exerciseDaoI.delete(2));
    }
}
