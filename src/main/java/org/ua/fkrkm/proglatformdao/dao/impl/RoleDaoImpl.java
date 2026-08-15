package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.Assert;
import org.ua.fkrkm.proglatformdao.dao.RoleDaoI;
import org.ua.fkrkm.proglatformdao.entity.Role;
import org.ua.fkrkm.proglatformdao.mappers.RoleMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з сутністю "Ролі"
 */
public class RoleDaoImpl extends ParentDaoImpl<Role> implements RoleDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public RoleDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("roles");
        setParam(":name");
        setValues("name");
        setValuesForUpdate("name = :name");
        setRowMapper(new RoleMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> findIdByName(String name) {
        Assert.notNull(name,
                "Назва ролі не може бути відсутня!");
        if (name.isBlank()) new ArrayList<>();
        String sql = "SELECT * FROM " + this.tableName + " WHERE name = :name;";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("name", name), new RoleMapper());
    }
}
