package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Завдання"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Exercise extends BaseEntity {
    // Назва завдання
    private String name;
    // Опис завдання
    private String description;
    // Оцінка
    private Integer assessment;
    // ID теми
    private Long topicId;
}
