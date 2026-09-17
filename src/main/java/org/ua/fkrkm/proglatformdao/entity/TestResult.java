package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Результат тестування
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class TestResult extends BaseEntity {
    private String testUuid;
    private Long userId;
    private Long maxAssessment;
    private Long assessment;
    private String correct;
    private String incorrect;
}
