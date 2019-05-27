package com.zereao.security.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Zereao
 * @version 2019/05/16 16:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends org.springframework.security.core.userdetails.User {
    private String name;
    private String password;
    private String token;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
