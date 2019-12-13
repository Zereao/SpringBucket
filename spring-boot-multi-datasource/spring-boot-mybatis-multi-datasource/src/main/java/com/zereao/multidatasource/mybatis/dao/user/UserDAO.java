package com.zereao.multidatasource.mybatis.dao.user;

import com.zereao.multidatasource.mybatis.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zereao
 * @version 2019/12/12 21:29
 */
@Mapper
public interface UserDAO {
    @Select({"   SELECT u.id, u.name, u.sex ",
            "      FROM user u ",
            "     WHERE u.id = #{id} "})
    User findById(@RequestParam("id") Integer id);
}
