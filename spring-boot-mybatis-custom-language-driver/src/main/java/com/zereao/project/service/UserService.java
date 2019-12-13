package com.zereao.project.service;

import com.zereao.project.entity.User;
import com.zereao.project.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author Zereao
 * @version 2019/12/13 14:09
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> getUserByIdAndRole(Collection<Integer> ids, String role) {
        return userMapper.getUserByIdAndRole(ids, role);
    }
}
