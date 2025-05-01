package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Role;

import java.util.List;

public interface RoleDaoI extends ParentDaoI<Role> {
    List<Integer> findIdByName(String name);
}
