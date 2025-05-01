package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Module;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuleMapper implements RowMapper<Module> {

    @Override
    public Module mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Module.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .courseId(rs.getInt("course_id"))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
