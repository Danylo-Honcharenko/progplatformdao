package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthMapper implements RowMapper<Auth> {

    @Override
    public Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Auth.builder()
                .id(rs.getInt("id"))
                .userId(rs.getInt("user_id"))
                .accessToken(rs.getString("access_token"))
                .created(rs.getTimestamp("created"))
                .expiresIn(rs.getTimestamp("expires_in"))
                .build();
    }
}
