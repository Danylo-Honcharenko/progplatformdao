package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 * Сутність "Аутентифіковані користувачі"
 */
@Data
@Builder
public class Auth {
    private Long id;
    private Long userId;
    private UUID sid;
    private Date created;
    private Date expiresAt;
    private Date revokedAt;
}
