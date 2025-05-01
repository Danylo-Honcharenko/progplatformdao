package dao;

import configuration.Database;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.ua.fkrkm.proglatformdao.dao.TestResultDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TestResultDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.TestResult;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResultImplTest {
    private final TestResultDaoI testResultDao = new TestResultDaoImpl(Database.getDataSource());

    @BeforeEach
    public void setUp() throws SQLException, IOException {
        DataSource dataSource = Database.getDataSource();
        Connection connection = dataSource.getConnection();

        RunScript.execute(connection, new FileReader(new ClassPathResource("dbT.sql").getFile()));
    }

    @Test
    public void createTest() {
        TestResult testResult = TestResult.builder()
                .testUuid("test")
                .userId(1)
                .maxAssessment(1)
                .assessment(1)
                .correct("{\"Test\": \"ok!\"}")
                .incorrect("{}")
                .created(new Date())
                .build();

        assertTrue(testResultDao.create(testResult) > 0);
    }

    @Test
    public void getByIdTest() {
        TestResult testResult = testResultDao.getById(1);
        assertEquals(1, testResult.getId());
    }

    @Test
    public void deleteTest() {
        assertEquals(1, testResultDao.delete(2));
    }

    @Test
    public void getTestResultByUserIdTest() {
        List<TestResult> results = testResultDao.getTestResultsByUserId(1);
        assertEquals(1, results.get(0).getId());
    }
}
