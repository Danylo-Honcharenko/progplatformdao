package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "User"
 */
public class UserMapper implements RowMapper<User> {
    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return User сутність
     * @throws SQLException виняток
     */
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .first_name(rs.getString("first_name"))
                .last_name(rs.getString("last_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .roleId(rs.getInt("role_id"))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
