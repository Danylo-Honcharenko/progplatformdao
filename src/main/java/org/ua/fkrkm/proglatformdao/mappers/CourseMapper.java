package org.ua.fkrkm.proglatformdao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.ua.fkrkm.proglatformdao.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Клас зіставлення рядків отриманих з бази даних з сутністю "Course"
 */
public class CourseMapper implements RowMapper<Course> {
    /**
     * Зіставлення рядків отриманих з бази даних з сутністю
     *
     * @param rs рядок отриманий з бази даних
     * @param rowNum номер рядка
     * @return Course сутність
     * @throws SQLException виняток
     */
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Course.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .created(rs.getTimestamp("created"))
                .updated(rs.getTimestamp("updated"))
                .build();
    }
}
