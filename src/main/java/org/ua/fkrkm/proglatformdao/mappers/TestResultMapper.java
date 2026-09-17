package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.TestResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultMapper implements RowMapper<TestResult> {

    @Override
    public TestResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TestResult.builder()
                .id(rs.getLong("id"))
                .testUuid(rs.getString("test_uuid"))
                .userId(rs.getLong("user_id"))
                .maxAssessment(rs.getLong("max_assessment"))
                .assessment(rs.getLong("assessment"))
                .correct(rs.getString("correct"))
                .incorrect(rs.getString("incorrect"))
                .created(rs.getTimestamp("created"))
                .build();
    }
}
