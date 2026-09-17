package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Модуль"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Module extends BaseEntity {
    private String name;
    private String description;
    private Long courseId;
}
