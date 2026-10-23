package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "Test".
 * Питання тесту завантажуються окремо
 */
public class TestMapper implements RowMapper<Test> {

    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs     рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return Test сутність
     * @throws SQLException виняток
     */
    @Override
    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Test.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .topicId(rs.getLong("topic_id"))
                .questions(new ArrayList<>())
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
