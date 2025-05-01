package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "Topic"
 */
public class TopicMapper implements RowMapper<Topic> {

    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return Topic сутність
     * @throws SQLException виняток
     */
    @Override
    public Topic mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Topic.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .moduleId(rs.getInt("module_id"))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
