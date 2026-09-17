package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.ModuleStat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModuleCompleteMapper implements RowMapper<ModuleStat> {

    @Override
    public ModuleStat mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ModuleStat.builder()
                .id(rs.getLong("id"))
                .moduleId(rs.getLong("module_id"))
                .topicId(rs.getLong("topic_id"))
                .userId(rs.getLong("user_id"))
                .build();
    }
}
