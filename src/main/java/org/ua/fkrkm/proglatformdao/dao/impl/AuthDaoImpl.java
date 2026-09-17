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
        setParam(":userId, :created, :expiresIn, :revoked");
        setValues("user_id, created, expires_in, revoked_at");
        setValuesForUpdate("user_id = :userId, created = :created, expires_in = :expiresIn, revoked_at = :revoked");
        setRowMapper(new AuthMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Auth> getByUserId(Long userId) {
        String sql = "SELECT * FROM " + this.tableName + " WHERE user_id = :userId;";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), new AuthMapper());
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void deleteByAccessToken(String accessToken) {
//        String sql = "DELETE FROM " + this.tableName + " WHERE access_token = :accessToken;";
//        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("accessToken", accessToken));
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAllTokensByUserId(Long userId) {
        String sql = "DELETE FROM " + this.tableName + " WHERE user_id = :userId AND expires_in < CURRENT_TIMESTAMP;";
        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("userId", userId));
    }
}
