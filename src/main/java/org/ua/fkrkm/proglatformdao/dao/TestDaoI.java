package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entityMongo.Test;

import java.util.List;

/**
 * Інтерфейс для роботи з документом "Тест"
 */
public interface TestDaoI {
    /**
     * Створити документ в базі
     *
     * @param entity сутність для зберігання
     * @return String UUID створеного запису
     */
    String create(Test entity);
    /**
     * Оновлення документа в базы
     *
     * @param entity сутність для оновлення
     */
    void update(Test entity);
    /**
     * Отримати документ по UUID
     *
     * @param uuid UUID документа в базі
     * @return Test запис з бази
     */
    Test getByUUID(String uuid);
    /**
     * Отримати тест по ID теми
     *
     * @param topicId ID теми
     * @return Test запис з бази
     */
    List<Test> getByTopicId(Integer topicId);
    /**
     * Отримати всі документи з бази
     *
     * @return List<Test> записи з бази
     */
    List<Test> getAll();
    /**
     * Видалити документ з бази
     *
     * @param uuid UUID документа в базі
     */
    void delete(String uuid);
}
