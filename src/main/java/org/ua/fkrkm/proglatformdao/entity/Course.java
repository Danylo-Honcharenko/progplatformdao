package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;

import java.util.Date;

/**
 * Сутність "Курси"
 */
@Data
@Builder
public class Course {
    private Integer id;
    // Назва курсу
    private String name;
    // Опис курсу
    private String description;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
