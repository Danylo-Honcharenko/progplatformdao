package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.ModuleStatDaoI;
import org.ua.fkrkm.proglatformdao.entity.ModuleStat;
import org.ua.fkrkm.proglatformdao.mappers.ModuleCompleteMapper;

import javax.sql.DataSource;
import java.util.List;

public class ModuleStatImpl extends ParentDaoImpl<ModuleStat> implements ModuleStatDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public ModuleStatImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("module_stat");
        setParam(":moduleId, :topicId, :userId");
        setValues("module_id, topic_id, user_id");
        setValuesForUpdate("module_id = :moduleId, topic_id = :topicId, user_id = :userId");
        setRowMapper(new ModuleCompleteMapper());
    }


    @Override
    public List<ModuleStat> findModuleStatByUserId(int userId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE user_id = :userId";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), new ModuleCompleteMapper());
    }
}
