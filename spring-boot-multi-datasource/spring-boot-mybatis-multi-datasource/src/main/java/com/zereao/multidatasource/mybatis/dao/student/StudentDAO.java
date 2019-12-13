package com.zereao.multidatasource.mybatis.dao.student;

import com.zereao.multidatasource.mybatis.entity.student.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zereao
 * @version 2019/12/12 21:29
 */
@Mapper
public interface StudentDAO {
    @Select({"   SELECT s.id, s.name, s.school ",
            "      FROM student s ",
            "     WHERE s.name = #{name} "})
    Student findByName(@RequestParam("name") String name);
}
