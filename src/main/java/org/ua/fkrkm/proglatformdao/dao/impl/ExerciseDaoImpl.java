package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.ExerciseDaoI;
import org.ua.fkrkm.proglatformdao.entity.Exercise;
import org.ua.fkrkm.proglatformdao.mappers.ExerciseMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Клас для роботи з сутністю "Завдання"
 */
public class ExerciseDaoImpl extends ParentDaoImpl<Exercise> implements ExerciseDaoI {
    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public ExerciseDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("exercises");
        setParam(":name, :description, :assessment, :topicId, :created");
        setValues("name, description, assessment, topic_id, created");
        setValuesForUpdate("name = :name, description = :description, assessment = :assessment, topic_id = :topicId, updated = :updated");
        setRowMapper(new ExerciseMapper());
    }


    @Override
    public List<Exercise> findExercisesByTopicId(int topicId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE topic_id = :topicId";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("topicId", topicId), new ExerciseMapper());
    }
}
