package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.util.List;

/**
 * DAO інтерфейс для сутності Auth
 */
public interface AuthDaoI extends ParentDaoI<Auth> {
    /**
     * Отримати аутентифікованого користувача
     *
     * @param userId ID користувача
     * @return List<Auth> список аутентифікованих користувачів
     */
    List<Auth> getByUserId(Long userId);
//    /**
//     * Видалити по токену
//     *
//     * @param accessToken токен
//     */
//    void deleteByAccessToken(String accessToken);
    /**
     * Видалення токену по ID користувача
     *
     * @param userId ID користувача
     */
    void deleteAllTokensByUserId(Long userId);
}
