package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.ModuleStat;
import org.ua.fkrkm.proglatformdao.entity.view.ModuleStateView;

import java.util.List;

/**
 * Інтерфейс для роботи з сутністю "Статистика по модулю"
 */
public interface ModuleStatDaoI extends ParentDaoI<ModuleStat>{
    List<ModuleStat> findModuleStatByUserId(Integer userId);
    /**
     * Отримати статистику по всім модулям по ID користувача
     *
     * @param userId ID користувача
     * @return List<ModuleStateView> статистика по модулям
     */
    List<ModuleStateView> findModulesStatByUserId(Integer userId);
}
