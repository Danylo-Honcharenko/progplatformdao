package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AuthMapper implements RowMapper<Auth> {

    @Override
    public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Auth.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .sid(rs.getObject("sid", UUID.class))
                .created(rs.getTimestamp("created"))
                .expiresAt(rs.getTimestamp("expires_at"))
                .revokedAt(rs.getTimestamp("revoked_at"))
                .build();
    }
}
