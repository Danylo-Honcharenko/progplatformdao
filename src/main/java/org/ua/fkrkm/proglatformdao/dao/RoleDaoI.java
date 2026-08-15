package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Role;

import java.util.List;

/**
 * Інтерфейс для роботи з ролями
 */
public interface RoleDaoI extends ParentDaoI<Role> {
    /**
     * Пошук ролі по назві
     *
     * @param name назва ролі
     * @return Role знайдена роль
     */
    List<Role> findIdByName(String name);
}
