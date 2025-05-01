package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Результат тестування
 */
@Data
@Builder
public class TestResult {
    private int id;
    private String testUuid;
    private int userId;
    private int maxAssessment;
    private int assessment;
    private String correct;
    private String incorrect;
    private Date created;
}
