package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.AuthDaoI;
import org.ua.fkrkm.proglatformdao.entity.Auth;
import org.ua.fkrkm.proglatformdao.mappers.AuthMapper;

import javax.sql.DataSource;
import java.util.List;

public class AuthDaoImpl extends ParentDaoImpl<Auth> implements AuthDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public AuthDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("auth");
        setParam(":userId, :accessToken, :created, :expiresIn");
        setValues("user_id, access_token, created, expires_in");
        setValuesForUpdate("user_id = :userId, access_token = :accessToken, created = :created, expires_in = :expiresIn");
        setRowMapper(new AuthMapper());
    }

    @Override
    public List<Auth> getByAccessToken(String accessToken) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE access_token = :accessToken;";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("accessToken", accessToken), new AuthMapper());
    }

    @Override
    public void deleteByAccessToken(String accessToken) {
        String sql = "DELETE FROM " + this.tableName + " WHERE access_token = :accessToken;";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("accessToken", accessToken));
    }
}
