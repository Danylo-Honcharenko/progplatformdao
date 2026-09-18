package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.AuthDaoI;
import org.ua.fkrkm.proglatformdao.entity.Auth;
import org.ua.fkrkm.proglatformdao.mappers.AuthMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

public class AuthDaoImpl extends ParentDaoImpl<Auth> implements AuthDaoI {

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public AuthDaoImpl(DataSource dataSource) {
        super(dataSource);
        setTableName("auth");
        setParam(":userId, :created, :sid, :expiresAt, :revokedAt");
        setValues("user_id, created, sid, expires_at, revoked_at");
        setValuesForUpdate("user_id = :userId, created = :created, sid = :sid, expires_at = :expiresAt, revoked_at = :revokedAt");
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void revokeByUserId(Long userId, String sid) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("userId", userId);
        sqlParameterSource.addValue("sid", UUID.fromString(sid));

        String sql = "UPDATE " + this.tableName + " SET revoked_at = CURRENT_TIMESTAMP WHERE user_id = :userId AND sid = :sid;";
        this.namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRevokedByUserId(Long userId, String sid) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("userId", userId);
        sqlParameterSource.addValue("sid", UUID.fromString(sid));

        String sql = "SELECT COUNT(*) AS COUNT FROM " + this.tableName + " WHERE user_id = :userId " +
                "AND revoked_at IS NULL " +
                "AND expires_at < CURRENT_TIMESTAMP " +
                "AND sid = :sid;";
        return this.namedParameterJdbcTemplate.query(sql, sqlParameterSource, (ResultSet rs, int rowNum) -> rs.getInt("COUNT") != 0).get(0);
    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void deleteByAccessToken(String accessToken) {
//        String sql = "DELETE FROM " + this.tableName + " WHERE access_token = :accessToken;";
//        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("accessToken", accessToken));
//    }

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void deleteAllTokensByUserId(Long userId) {
//        String sql = "DELETE FROM " + this.tableName + " WHERE user_id = :userId AND expires_in < CURRENT_TIMESTAMP;";
//        this.namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("userId", userId));
//    }
}
