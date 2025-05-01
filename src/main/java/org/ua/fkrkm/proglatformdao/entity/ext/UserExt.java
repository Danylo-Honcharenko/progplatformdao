package org.ua.fkrkm.proglatformdao.entity.ext;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.ua.fkrkm.proglatformdao.entity.User;

import java.util.Collection;

@Getter
@Setter
public class UserExt extends User {
    // Роль
    private Collection<? extends GrantedAuthority> authorities;
}
