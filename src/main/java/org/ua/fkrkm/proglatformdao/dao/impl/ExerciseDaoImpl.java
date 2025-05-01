package org.ua.fkrkm.proglatformdao.dao.impl;

import org.ua.fkrkm.proglatformdao.dao.ExerciseDaoI;
import org.ua.fkrkm.proglatformdao.entity.Exercise;
import org.ua.fkrkm.proglatformdao.mappers.ExerciseMapper;

import javax.sql.DataSource;

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
        setParam(":name, :description, :topicId, :created");
        setValues("name, description, topic_id, created");
        setValuesForUpdate("name = :name, description = :description, topic_id = :topicId, updated = :updated");
        setRowMapper(new ExerciseMapper());
    }
}
