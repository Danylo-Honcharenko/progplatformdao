package org.ua.fkrkm.proglatformdao.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Базовий клас для сутностей
 */
@Data
@SuperBuilder
public class BaseEntity {
    // Ідентифікатор
    private Long id;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
