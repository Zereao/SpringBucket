package com.zereao.multidatasource.mybatis.entity.user;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/12/13 11:34
 */
@Data
public class User {
    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别；0 - 男，1 - 女
     */
    private Integer sex;
}
