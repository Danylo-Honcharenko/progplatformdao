package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.util.List;
import java.util.UUID;

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
    /**
     * Скасувати аутентифікацію користувача
     *
     * @param userId ID користувача
     */
    void revokeByUserId(Long userId, String sid);
    /**
     * Перевірити, чи скасована аутентифікація користувача
     *
     * @param userId ID користувача
     * @return boolean true/false
     */
    boolean isRevokedByUserId(Long userId, String sid);
//    /**
//     * Видалити по токену
//     *
//     * @param accessToken токен
//     */
//    void deleteByAccessToken(String accessToken);
//    /**
//     * Видалення токену по ID користувача
//     *
//     * @param userId ID користувача
//     */
//    void deleteAllTokensByUserId(Long userId);
}
