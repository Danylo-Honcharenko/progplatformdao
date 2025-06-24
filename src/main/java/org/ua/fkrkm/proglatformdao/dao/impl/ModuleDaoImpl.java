package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.ModuleDaoI;
import org.ua.fkrkm.proglatformdao.entity.Module;
import org.ua.fkrkm.proglatformdao.mappers.ModuleMapper;

import javax.sql.DataSource;
import java.util.List;

public class ModuleDaoImpl extends ParentDaoImpl<Module> implements ModuleDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public ModuleDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("module");
        setParam(":name, :description, :courseId, :created, :updated");
        setValues("name, description, course_id, created, updated");
        setValuesForUpdate("name = :name, description = :description, course_id = :courseId, updated = :updated");
        setRowMapper(new ModuleMapper());
    }

    @Override
    public List<Module> getModulesByCourseId(int courseId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE course_id = :courseId ORDER BY id";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("courseId", courseId), new ModuleMapper());
    }
}
