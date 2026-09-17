package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Тема"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Topic extends BaseEntity {
    // Назва теми
    private String name;
    // Опис теми
    private String description;
    // ID курсу
    private Long moduleId;
}
