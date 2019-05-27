package com.zereao.security.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zereao
 * @version 2019/05/17 11:36
 */
@Service
public class TokenLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        // 这个方法中执行相关逻辑，一般是用于  清除当前用户的 token/session 信息等
    }
}
