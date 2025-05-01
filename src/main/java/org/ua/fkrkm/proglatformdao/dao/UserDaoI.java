package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.User;

import java.util.List;

/**
 * Інтрефейс для DAO користувачів
 */
public interface UserDaoI extends ParentDaoI<User> {
    /**
     * Знайти користувача по email
     *
     * @param email email
     * @return List<User> користувачі
     */
    List<User> findByEmail(String email);

    /**
     * Знайти користувача по параметрам
     *
     * @param id ID користувача
     * @param firstName імя
     * @param lastName фамілія
     * @param email email
     * @return List<User> користувачі
     */
    List<User> findByParams(Integer id, String firstName, String lastName, String email);

    /**
     * Знайти всіх користувачів
     *
     * @param limit ліміт записів
     * @return List<User> користувачі
     */
    List<User> findAll(Integer limit);
}
