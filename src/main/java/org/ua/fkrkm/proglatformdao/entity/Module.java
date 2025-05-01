package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Сутність "Модуль"
 */
@Data
@Builder
public class Module {
    private int id;
    private String name;
    private String description;
    private int courseId;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
