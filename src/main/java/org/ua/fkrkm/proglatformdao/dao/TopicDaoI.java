package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Topic;

import java.util.List;

/**
 * Інтерфейс для роботи з сутністю "Тема"
 */
public interface TopicDaoI extends ParentDaoI<Topic> {
    /**
     * Знайти всі теми модуля
     *
     * @param moduleId ID модуля
     * @return List<Topic> список тем
     */
    List<Topic> findAllTopicsByModuleId(int moduleId);
}
