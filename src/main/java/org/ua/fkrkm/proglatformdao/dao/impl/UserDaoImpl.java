package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.UserDaoI;
import org.ua.fkrkm.proglatformdao.entity.User;
import org.ua.fkrkm.proglatformdao.mappers.UserMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Клас для роботи з сутністю "Користувач"
 */
public class UserDaoImpl extends ParentDaoImpl<User> implements UserDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public UserDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("users");
        setParam(":first_name, :last_name, :email, :password, :roleId, :created");
        setValues("first_name, last_name, email, password, role_id, created");
        setValuesForUpdate("first_name = :first_name, last_name = :last_name, email = :email, password = :password, role_id = :roleId, updated = :updated");
        setRowMapper(new UserMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findByEmail(String email) {
        return findByParams(null, null, null, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findByParams(Integer id, String firstName, String lastName, String email) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "SELECT * FROM " + this.tableName + " WHERE 1 = 1 ";

        if (id != null) {
            sql += "AND id = :id";
            params.addValue("id", id);
        }

        if (firstName != null) {
            sql += "AND first_name = :firstName";
            params.addValue("firstName", firstName);
        }

        if (lastName != null) {
            sql +=  "AND last_name = :lasName";
            params.addValue("lasName", lastName);
        }

        if (email != null) {
            sql +=  "AND email = :email";
            params.addValue("email", email);
        }

        return this.namedParameterJdbcTemplate.query(sql, params, new UserMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> findAll(Integer limit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        String sql = "SELECT * FROM " + this.tableName;

        if (limit > 0) {
            sql += " LIMIT :limit";
            params.addValue("limit", limit);
        }

        return this.namedParameterJdbcTemplate.query(sql, params, new UserMapper());
    }
}
