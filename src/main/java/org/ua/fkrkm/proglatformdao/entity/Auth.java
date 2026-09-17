package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Сутність "Аутентифіковані користувачі"
 */
@Data
@Builder
public class Auth {
    private Long id;
    private Long userId;
    private Date created;
    private Date expiresIn;
    private Date revoked;
}
