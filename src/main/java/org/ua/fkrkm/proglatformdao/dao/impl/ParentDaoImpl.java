package org.ua.fkrkm.proglatformdao.dao.impl;

import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;
import org.ua.fkrkm.proglatformdao.dao.ParentDaoI;
import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

/**
 * Мастер DAO. DAO з CRUD операціями та часто використовуваними
 *
 * @param <T> сутність
 */
public class ParentDaoImpl<T> implements ParentDaoI<T> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    // Назва таблиці
    @Setter
    protected String tableName;
    // Параметри замісить яких буде підставлено значення
    @Setter
    private String param;
    // Всі поля запису
    @Setter
    private String values;
    // Зіставлені поля запису та параметри
    @Setter
    private String valuesForUpdate;
    // Маппер
    @Setter
    private RowMapper<T> rowMapper;

    /**
     * Конструктор
     *
     * @param dataSource об'єкт джерела бази даних
     */
    public ParentDaoImpl(DataSource dataSource) {
        Assert.notNull(dataSource,
                "DataSource must not be null!");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int create(T entity) {
        // Об'єкт для зберігання згенерованого ключа
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        // Підставляємо параметри в запит (наприк. замість :name "Jack")
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(entity);
        // Запит
        String sql = "INSERT INTO " + tableName + "(" + values + ")" + "VALUES " + "(" + param + ");";
        // Робимо запит
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder, new String[]{"id"});
        // Повертаємо ключ створенного запису та перевіряємо, щоб він був присутній
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(T entity) {
        // Підставляємо параметри в запит
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(entity);
        // Запит
        String sql = "UPDATE " + tableName + " SET " + valuesForUpdate + "  WHERE id = :id;";
        // Робимо запит
        return namedParameterJdbcTemplate.update(sql, sqlParameterSource);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getById(int id) {
        // Запит
        String sql = "SELECT * FROM " + this.tableName + " WHERE id = :id";
        // Підставляємо параметри в запит
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        // Робимо запит
        return this.namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, rowMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> getAll() {
        String sql = "SELECT * FROM " + this.tableName;
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(int id) {
        // Запит
        String sql = "DELETE FROM " + tableName + " WHERE id = :id;";
        // Робимо запит
        return namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }
}
