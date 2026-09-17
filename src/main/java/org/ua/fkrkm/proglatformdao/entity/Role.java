package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Роль"
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    // Назва ролі
    private String name;
}
