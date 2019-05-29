package com.zereao.security.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

/**
 * 校验Token
 *
 * @author Zereao
 * @version 2019/05/16 19:13
 */
@Slf4j
@Configuration
public class TokenAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = ((HttpServletRequest) servletRequest).getHeader("token");
        // token 为空，就继续下一个Filter
        if (StringUtils.isEmpty(token)) {
            log.info("token为空，进入下一环！");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = null;
        if ("THE_TOKEN".equals(token)) {
            String userId = /*parseToken(token)*/ "THE_USER_ID";
            authentication = new UsernamePasswordAuthenticationToken(userId, token, Collections.emptyList());
        }
        if (authentication != null) {
            // 如果authentication不为空，将其设置到 SecurityContextHolder 中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        log.info("token校验完毕，进入下一环！");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Bean
    public FilterRegistrationBean<TokenAuthenticationFilter> tokenAuthFilterRegBean(TokenAuthenticationFilter filter) {
        FilterRegistrationBean<TokenAuthenticationFilter> regBean = new FilterRegistrationBean<>();
        regBean.setFilter(filter);
        regBean.setEnabled(false);
        return regBean;
    }
}
