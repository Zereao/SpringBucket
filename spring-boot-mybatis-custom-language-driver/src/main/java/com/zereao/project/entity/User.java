package com.zereao.project.entity;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/12/13 14:20
 */
@Data
public class User {
    /**
     * ID
     */
    private Integer id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户角色
     */
    private String role;
}
