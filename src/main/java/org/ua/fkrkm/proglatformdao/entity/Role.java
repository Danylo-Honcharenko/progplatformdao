package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;

/**
 * Сутність "Роль"
 */
@Data
@Builder
public class Role {
    private Integer id;
    // Назва ролі
    private String name;
}
