package com.zereao.project.mapper;

import com.zereao.project.config.CustomXmlLanguageDriver;
import com.zereao.project.entity.User;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * @author Zereao
 * @version 2019/12/13 14:08
 */
@Mapper
public interface UserMapper {
    @Lang(CustomXmlLanguageDriver.class)
    @Select({"  SELECT u.id, u.account, u.role ",
            "     FROM user u",
            "    WHERE u.id IN (#{ids}) ",
            "      if (role != null and role != '') [AND u.role = #{role}] "})
    List<User> getUserByIdAndRole(@RequestParam("ids") Collection<Integer> ids, @RequestParam("role") String role);
}
