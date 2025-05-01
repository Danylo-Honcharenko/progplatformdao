package org.ua.fkrkm.proglatformdao.dao;

import org.ua.fkrkm.proglatformdao.entity.TestResult;

import java.util.List;

public interface TestResultDaoI extends ParentDaoI<TestResult> {
    List<TestResult> getTestResultsByUserId(Integer userId);
}
