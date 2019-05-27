package com.zereao.security.security.filter;

import com.zereao.security.po.TheUser;
import com.zereao.security.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自己实现的 UsernamePasswordAuthenticationFilter，实际上， UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter;
 * AbstractAuthenticationProcessingFilter是基于浏览器和 HTTP 认证请求的处理器，可以理解为它就是 Spring Security 认证流程的入口。
 * Spring Security 提供了AbstractAuthenticationProcessingFilter的几个实现类：
 * CasAuthenticationFilter
 * OAuth2LoginAuthenticationFilter
 * OpenIDAuthenticationFilter
 * UsernamePasswordAuthenticationFilter
 * 最常使用的应该是 UsernamePasswordAuthenticationFilter，其它类都应用于特定的场景。
 *
 * @author Zereao
 * @version 2019/05/16 17:44
 */
@Slf4j
@Service
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    //    @Value("${FilterProcessesUrl}")
    private String filterProcessesUrl = "/login";

    private static final String FILTER_APPLIED = "__spring_security_tokenLoginFilter_filter_Applied";

    /**
     * 这里，设置当前 Filter 只处理 /login 这个URI路径
     */
    @PostConstruct
    public void setFilterProcessesUrl() {
        this.setFilterProcessesUrl(filterProcessesUrl);
    }

    /**
     * 需要在这里注入一下，否则报错：java.lang.IllegalArgumentException: authenticationManager must be specified
     */
    @Resource
    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 如果拦截到 OPTIONS 请求，则抛出异常
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            throw new ProviderNotFoundException("OPTIONS");
        }
        // 从 request 中解析出 username 和 password
        // 假设的下面的数据是从 request 中解析出来的
        TheUser user = new TheUser();
        String username = user.getName();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        // 执行到这里，执行 authenticate() 方法后，就交由 UserService.loadUserByUsername() 方法处理
        log.info("数据准备完毕，准备进入UserDetailService校验！");
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 这里执行 getPrincipal() 方法，返回的对象就是 UserService.loadUserByUsername()方法检验成功后返回的对象
        User user = (User) authResult.getPrincipal();
        // 这里，可以调用方法生成Token，下次直接校验token即可。这里假定下面的token就是生成完毕的token
        String token = user.getToken();
        ResponseEntity<String> entity = ResponseEntity.ok(token);
        log.info("TokenLoginFilter 校验成功！");
        response.getWriter().print(entity.toString());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        // 执行到这里，说明 UserService.loadUserByUsername()方法校验失败了
        String result = "校验失败啦！";
        ResponseEntity<String> entity = ResponseEntity.ok(result);
        log.info("TokenLoginFilter 校验失败！");
        response.getWriter().print(entity.toString());
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getAttribute(FILTER_APPLIED) != null) {
            log.info("TokenLoginFilter 已经被加载过，直接进入下一个过滤器！");
            chain.doFilter(req, res);
            return;
        }
        // 在spring容器托管的GenericFilterBean的bean，都会自动加入到servlet的filter chain（MVC的Filter链中）
        // 而根据WebSecurityConfig里面的定义，还额外把filter加入到了spring security的Filter链中。
        // 而spring security也是一系列的filter，在mvc的filter之前执行。因此在鉴权通过的情况下，就会先后各执行一次。
        // https://segmentfault.com/a/1190000012173419
        req.setAttribute(FILTER_APPLIED, true);
        super.doFilter(req, res, chain);
    }
}
