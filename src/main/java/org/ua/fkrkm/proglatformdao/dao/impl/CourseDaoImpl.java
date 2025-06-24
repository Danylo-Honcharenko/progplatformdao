package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.CourseDaoI;
import org.ua.fkrkm.proglatformdao.entity.Course;
import org.ua.fkrkm.proglatformdao.mappers.CourseMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Клас для роботи з сутністю "Курси"
 */
public class CourseDaoImpl extends ParentDaoImpl<Course> implements CourseDaoI {

    /**
     * Конструктор
     * 
     * @param dataSource об'єкт джерела бази даних
     */
    public CourseDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("courses");
        setParam(":name, :description, :created");
        setValues("name, description, created");
        setValuesForUpdate("name = :name, description = :description, updated = :updated");
        setRowMapper(new CourseMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserToCourse(int courseId, int userId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("courseId", courseId)
                .addValue("userId", userId);
        String sql = "INSERT INTO user_course (user_id, course_id) VALUES (:userId, :courseId);";
        this.namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAllUsersFromCourse(Integer courseId) {
        removeUserFromCourse(courseId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeUserFromCourse(Integer courseId, Integer userId) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        String sql = "DELETE FROM user_course WHERE 1 = 1 ";

        if(courseId != null) {
            sql += "AND course_id = :courseId";
            sqlParameterSource.addValue("courseId", courseId);
        }

        if (userId != null) {
            sql += "AND user_id = :userId";
            sqlParameterSource.addValue("userId", userId);
        }

        this.namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getCourseUsersIdByCourseId(int courseId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("courseId", courseId);
        String sql = "SELECT user_id FROM user_course WHERE course_id = :courseId;";
        return this.namedParameterJdbcTemplate.queryForList(sql, sqlParameterSource, Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Course> getCoursesIdByUserId(int userId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("userId", userId);
        String sql = "SELECT * FROM courses WHERE id IN (SELECT course_id FROM user_course WHERE user_id = :userId) ORDER BY id;";
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, new CourseMapper());
    }
}
