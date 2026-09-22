package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.ExerciseDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.ExerciseDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.Exercise;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExerciseDaoTest {

    private ExerciseDaoI exerciseDaoI;

    @BeforeEach
    public void setUp() {
        this.exerciseDaoI = new ExerciseDaoImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        Exercise exercise = Exercise.builder()
                .name("Exercise 2")
                .description("Exercise")
                .assessment(12)
                .topicId(2L)
                .created(new Date())
                .build();

        assertTrue(exerciseDaoI.create(exercise) > 0);
    }

    @Test
    public void updateTest() {
        Exercise updated = Exercise.builder()
                .id(1L)
                .name("Exercise 1")
                .description("Exercise")
                .assessment(11)
                .topicId(2L)
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
        List<Exercise> exercise = exerciseDaoI.getById(1L);
        assertFalse(CollectionUtils.isEmpty(exercise));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, exerciseDaoI.delete(2L));
    }
}
