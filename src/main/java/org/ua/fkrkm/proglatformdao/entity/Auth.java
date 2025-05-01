package org.ua.fkrkm.proglatformdao.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Сутність "Аунтифіковані користувачі"
 */
@Data
@Builder
public class Auth {
    private int id;
    private int userId;
    private String accessToken;
    private Date created;
    private Date expiresIn;
}
