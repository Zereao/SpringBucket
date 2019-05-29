package com.zereao.security.security.config;

import com.zereao.security.security.SimplePasswordEncoder;
import com.zereao.security.security.TokenLogoutHandler;
import com.zereao.security.security.UnauthorizedEntryPoint;
import com.zereao.security.security.filter.TokenAuthenticationFilter;
import com.zereao.security.security.filter.TokenLoginFilter;
import com.zereao.security.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

//import com.zereao.security.security.filter.TokenAuthenticationFilter;

/**
 * @author Zereao
 * @version 2019/05/16 16:29
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private TokenLoginFilter tokenLoginFilter;
    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;
    @Resource
    private TokenLogoutHandler logoutHandler;
    @Resource
    private UnauthorizedEntryPoint entryPoint;
    @Resource
    private UserService userService;
    @Resource
    private SimplePasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] permitUrl = new String[]{
                "/login",
                "/v2/public/**",
                "/v2/info/**",
                "/v2/region/**",
                "/v2/app-version/**"
        };
        http
                // 跨域支持 CORS
                .cors()
                // 跨域请求伪造 禁用
                .and().csrf().disable()
                // permitUrl中配置的uri全部放行，其他的全部需要校验
                .authorizeRequests().antMatchers(permitUrl).permitAll().anyRequest().authenticated()
                .and().addFilter(tokenLoginFilter).addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/logout").addLogoutHandler(logoutHandler)
                .and().exceptionHandling().authenticationEntryPoint(entryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
