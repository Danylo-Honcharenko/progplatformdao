package org.ua.fkrkm.proglatformdao.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.TopicDaoI;
import org.ua.fkrkm.proglatformdao.entity.Topic;
import org.ua.fkrkm.proglatformdao.mappers.TopicMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Клас для роботи з сутністю "Тема"
 */
public class TopicDaoImpl extends ParentDaoImpl<Topic> implements TopicDaoI {
    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public TopicDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("topics");
        setParam(":name, :description, :moduleId, :created");
        setValues("name, description, module_id, created");
        setValuesForUpdate("name = :name, description = :description, module_id = :moduleId, updated = :updated");
        setRowMapper(new TopicMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> findAllTopicsByModuleId(int moduleId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE module_id = :moduleId ORDER BY id;";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("moduleId", moduleId), new TopicMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Topic> findAllTopicsByModuleIdList(List<Integer> moduleIds) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE module_id IN (%s) ORDER BY module_id;";
        return this.jdbcTemplate.query(String.format(sql, StringUtils.join(moduleIds, ",")), new TopicMapper());
    }
}
