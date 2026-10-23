package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Test;

import java.util.List;

/**
 * Інтерфейс для роботи з сутністю "Тест". Разом з тестом зберігаються та завантажуються його питання
 */
public interface TestDaoI extends ParentDaoI<Test> {
    /**
     * Отримати тести по ID теми
     *
     * @param topicId ID теми
     * @return List<Test> тести теми разом з питаннями
     */
    List<Test> getByTopicId(Long topicId);
}
