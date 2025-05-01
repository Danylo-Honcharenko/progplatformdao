package org.ua.fkrkm.proglatformdao.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Сутність "Користувач"
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    // Імя
    private String first_name;
    // Фамілія
    private String last_name;
    // Email
    private String email;
    // Пароль
    private String password;
    // ID ролі
    private Integer roleId;
    // Час створення
    private Date created;
    // Час оновлення
    private Date updated;
}
