package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Module;

import java.util.List;

public interface ModuleDaoI extends ParentDaoI<Module> {
    List<Module> getModulesByCourseId(int courseId);
}
