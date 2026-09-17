package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Сутність "Користувач"
 */
@Getter
@Setter
@SuperBuilder
public class User extends BaseEntity {
    // Імя
    private String first_name;
    // Фамілія
    private String last_name;
    // Email
    private String email;
    // Пароль
    private String password;
    // ID ролі
    private Long roleId;
}
