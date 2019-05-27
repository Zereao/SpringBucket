package com.zereao.security.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
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
@Service
public class TokenAuthenticationFilter extends GenericFilterBean {

    private static final String FILTER_APPLIED = "__spring_security_tokenAuthenticationFilter_filter_Applied";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest.getAttribute(FILTER_APPLIED) != null) {
            log.info("TokenAuthenticationFilter 已经被加载过了，直接进入Filter链中的下一环！");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = ((HttpServletRequest) servletRequest).getHeader("token");
        servletRequest.setAttribute(FILTER_APPLIED, true);
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
}
