package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.util.List;

/**
 * DAO інтерфейс для сутності Auth
 */
public interface AuthDaoI extends ParentDaoI<Auth> {
    /**
     * Отримати аутентифікованого користувача по токену
     *
     * @param accessToken токен
     * @return List<Auth> список аутентифікованих користувачів
     */
    List<Auth> getByAccessToken(String accessToken);
    /**
     * Видалити по токену
     *
     * @param accessToken токен
     */
    void deleteByAccessToken(String accessToken);
}
