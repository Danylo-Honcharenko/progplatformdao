package dao;

import configuration.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;
import org.ua.fkrkm.proglatformdao.dao.TestResultDaoI;
import org.ua.fkrkm.proglatformdao.dao.impl.TestResultDaoImpl;
import org.ua.fkrkm.proglatformdao.entity.TestResult;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestResultImplTest {

    private TestResultDaoI testResultDao;

    @BeforeEach
    public void setUp() {
        this.testResultDao = new TestResultDaoImpl(Database.getDataSource());
    }

    @Test
    public void createTest() {
        TestResult testResult = TestResult.builder()
                .testUuid("test")
                .userId(1L)
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
        List<TestResult> testResult = testResultDao.getById(1L);
        assertFalse(CollectionUtils.isEmpty(testResult));
    }

    @Test
    public void deleteTest() {
        assertEquals(1, testResultDao.delete(2L));
    }

    @Test
    public void getTestResultByUserIdTest() {
        List<TestResult> results = testResultDao.getTestResultsByUserId(1L);
        assertEquals(1, results.get(results.size() - 1).getId());
    }
}
