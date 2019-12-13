package com.zereao.multidatasource.mybatis.service;

import com.zereao.multidatasource.mybatis.dao.student.StudentDAO;
import com.zereao.multidatasource.mybatis.dao.user.UserDAO;
import com.zereao.multidatasource.mybatis.entity.student.Student;
import com.zereao.multidatasource.mybatis.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Zereao
 * @version 2019/12/12 21:30
 */
@Slf4j
@Service
public class UserStudentService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private StudentDAO studentDAO;

    public Student getStudentByUserId(Integer userId) {
        User user = userDAO.findById(userId);
        if (user == null) {
            log.info("ID = {}的用户不存在！", userId);
            return null;
        }
        String userName = user.getName();
        return studentDAO.findByName(userName);
    }
}
