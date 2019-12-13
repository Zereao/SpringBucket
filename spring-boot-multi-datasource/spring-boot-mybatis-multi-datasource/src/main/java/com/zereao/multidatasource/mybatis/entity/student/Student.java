package com.zereao.multidatasource.mybatis.entity.student;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/12/13 11:34
 */
@Data
public class Student {
    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学校
     */
    private String school;
}
