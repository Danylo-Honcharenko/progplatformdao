package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Курси"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Course extends BaseEntity {
    // Назва курсу
    private String name;
    // Опис курсу
    private String description;
}
