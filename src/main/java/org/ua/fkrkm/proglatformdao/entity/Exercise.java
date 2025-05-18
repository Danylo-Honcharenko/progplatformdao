package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;

import java.util.Date;

/**
 * Сутність "Завдання"
 */
@Data
@Builder
public class Exercise {
    private Integer id;
    // Назва завдання
    private String name;
    // Опис завдання
    private String description;
    // Оцінка
    private Integer assessment;
    // ID теми
    private Integer topicId;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
