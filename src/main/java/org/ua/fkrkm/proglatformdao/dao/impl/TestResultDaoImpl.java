package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.TestResultDaoI;
import org.ua.fkrkm.proglatformdao.entity.TestResult;
import org.ua.fkrkm.proglatformdao.mappers.TestResultMapper;

import javax.sql.DataSource;
import java.util.List;

public class TestResultDaoImpl extends ParentDaoImpl<TestResult> implements TestResultDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public TestResultDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("test_result");
        setParam(":testUuid, :userId, :maxAssessment, :assessment, :correct::jsonb, :incorrect::jsonb, :created");
        setValues("test_uuid, user_id, max_assessment, assessment, correct, incorrect, created");
        setValuesForUpdate("test_uuid = :testUuid, user_id = :userId, max_assessment = :maxAssessment, assessment = :assessment, correct = :correct::jsonb, incorrect = :incorrect::jsonb, created = :created");
        setRowMapper(new TestResultMapper());
    }

    @Override
    public List<TestResult> getTestResultsByUserId(Integer userId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE user_id = :userId";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new TestResultMapper());
    }
}
