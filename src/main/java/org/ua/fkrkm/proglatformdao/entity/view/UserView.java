package org.ua.fkrkm.proglatformdao.entity.view;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * Відображення користувачів
 */
@Data
@Builder
public class UserView {
    int id;
    // Імя
    private String firstName;
    // Фамілія
    private String lastName;
    // Email
    private String email;
    // Роль користувача
    private String role;
    // Час створення акаунту
    private Date created;
}
