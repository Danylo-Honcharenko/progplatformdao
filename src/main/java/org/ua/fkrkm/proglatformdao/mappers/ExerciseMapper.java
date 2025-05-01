package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Exercise;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "Exercise"
 */
public class ExerciseMapper implements RowMapper<Exercise> {

    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return Exercise сутність
     * @throws SQLException виняток
     */
    @Override
    public Exercise mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Exercise.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .topicId(rs.getInt("topic_id"))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
