package com.zereao.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 自定义的密码加密/解密器
 *
 * @author 何雨伦
 * @version 2018/10/16  17:26
 */
@Service
public class SimplePasswordEncoder implements PasswordEncoder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            logger.warn("Empty encoded password");
            return false;
        }
        return Objects.equals(rawPassword, encodedPassword);
    }
}
