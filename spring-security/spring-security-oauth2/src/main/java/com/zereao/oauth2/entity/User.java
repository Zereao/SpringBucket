package com.zereao.oauth2.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author Zereao
 * @version 2019/05/28 10:58
 */
public class User extends org.springframework.security.core.userdetails.User {


    public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
