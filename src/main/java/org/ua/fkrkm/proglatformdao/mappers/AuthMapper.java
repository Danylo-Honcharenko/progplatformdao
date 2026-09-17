package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthMapper implements RowMapper<Auth> {

    @Override
    public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Auth.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .created(rs.getTimestamp("created"))
                .expiresIn(rs.getTimestamp("expires_in"))
                .revoked(rs.getTimestamp("revoked_at"))
                .build();
    }
}
