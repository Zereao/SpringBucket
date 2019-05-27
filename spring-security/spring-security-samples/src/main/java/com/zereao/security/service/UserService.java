package com.zereao.security.service;

import com.zereao.security.po.TheUser;
import com.zereao.security.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Zereao
 * @version 2019/05/16 16:29
 */
@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 假定 user 就是我们从数据库、Redis 中读取到的用户信息对象
        User authedUser = null;
        TheUser user = new TheUser();
        if (user.getName().equals(username)) {
            authedUser = new User(username, user.getPassword(), Collections.emptyList());
        }
        // 处理完毕后，这里返回我们的User，然后执行  TokenLoginFilter中的 successfulAuthentication() 或者 unsuccessfulAuthentication()
        log.info("校验完毕，authedUser = {}", authedUser);
        return authedUser;
    }
}
