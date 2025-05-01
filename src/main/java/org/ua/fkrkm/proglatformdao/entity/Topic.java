package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;

import java.util.Date;

/**
 * Сутність "Тема"
 */
@Data
@Builder
public class Topic {
    private Integer id;
    // Назва теми
    private String name;
    // Опис теми
    private String description;
    // ID курсу
    private Integer moduleId;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
