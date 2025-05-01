package org.ua.fkrkm.proglatformdao.dao;

import java.util.List;

/**
 * Интерфейс для мастера DAO
 *
 * @param <T> сутність
 */
public interface ParentDaoI<T> {
    /**
     * Створити запис
     *
     * @param entity сутність
     * @return int ID створеного запису в базі
     */
    int create(T entity);
    /**
     * Оновити запис
     *
     * @param entity сутність
     * @return T кількість оновлених записів
     */
    int update(T entity);
    /**
     * Отримати запис по ID
     *
     * @param id ID запису
     * @return T сутність
     */
    T getById(int id);
    /**
     * Отримати всі записи
     *
     * @return List<T> всі записи
     */
    List<T> getAll();
    /**
     * Видалити запис
     *
     * @param id ID запису для видалення
     * @return int кількість видалених записів
     */
    int delete(int id);
}
