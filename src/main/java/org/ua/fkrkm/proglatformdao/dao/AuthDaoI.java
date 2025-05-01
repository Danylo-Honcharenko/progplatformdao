package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.Auth;

import java.util.List;

public interface AuthDaoI extends ParentDaoI<Auth> {
    List<Auth> getByAccessToken(String accessToken);
    void deleteByAccessToken(String accessToken);
}
