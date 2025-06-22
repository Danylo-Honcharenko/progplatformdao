package org.ua.fkrkm.proglatformdao.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.ua.fkrkm.proglatformdao.dao.ModuleStatDaoI;
import org.ua.fkrkm.proglatformdao.entity.ModuleStat;
import org.ua.fkrkm.proglatformdao.entity.view.ModuleStateView;
import org.ua.fkrkm.proglatformdao.mappers.ModuleCompleteMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з сутністю "Статистика по модулю"
 */
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
    public List<ModuleStat> findModuleStatByUserId(Integer userId) {
        if (userId == null) return new ArrayList<>();
        String sql = "SELECT * FROM " + this.tableName + " WHERE user_id = :userId";
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), new ModuleCompleteMapper());
    }

    private final RowMapper<ModuleStateView> MODULE_STAT_ROW_MAPPER = (ResultSet rs, int rowNum) -> ModuleStateView.builder()
            .moduleId(rs.getInt("module_id"))
            .moduleCompletionPercentage(rs.getBigDecimal("complited"))
            .build();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ModuleStateView> findModulesStatByUserId(Integer userId) {
        if (userId == null) return new ArrayList<>();
        String sql = """
                WITH complite_module_by_user_id AS (
                    SELECT COUNT(ms.module_id) AS complite_topic_count, module_id FROM module_stat AS ms WHERE ms.user_id = :userId GROUP BY ms.module_id
                ),
                module_topics_count AS (
                    SELECT COUNT(t.module_id) AS topic_count, t.module_id FROM topics AS t GROUP BY t.module_id
                )
                SELECT cmbui.module_id, ROUND(CAST((cmbui.complite_topic_count::float / mtc.topic_count::float) * 100 AS DECIMAL), 2) AS complited FROM complite_module_by_user_id AS cmbui, module_topics_count AS mtc WHERE cmbui.module_id = mtc.module_id ORDER BY cmbui.module_id;
                """;
        return this.namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), MODULE_STAT_ROW_MAPPER);
    }
}
