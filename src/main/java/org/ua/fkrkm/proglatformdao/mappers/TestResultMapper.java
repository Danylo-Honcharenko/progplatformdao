package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.TestResult;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestResultMapper implements RowMapper<TestResult> {

    @Override
    public TestResult mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TestResult.builder()
                .id(rs.getInt("id"))
                .testUuid(rs.getString("test_uuid"))
                .userId(rs.getInt("user_id"))
                .maxAssessment(rs.getInt("max_assessment"))
                .assessment(rs.getInt("assessment"))
                .correct(rs.getString("correct"))
                .incorrect(rs.getString("incorrect"))
                .created(rs.getDate("created"))
                .build();
    }
}
