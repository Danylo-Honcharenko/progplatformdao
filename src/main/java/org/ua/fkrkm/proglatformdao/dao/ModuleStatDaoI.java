package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.ModuleStat;

import java.util.List;

public interface ModuleStatDaoI extends ParentDaoI<ModuleStat>{
    List<ModuleStat> findModuleStatByUserId(int userId);
}
