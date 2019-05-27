package com.zereao.security.po;

import lombok.Data;

/**
 * 模拟从数据库中查询到的某个User
 *
 * @author Zereao
 * @version 2019/05/16 16:32
 */
@Data
public class TheUser {
    private String name = "zhangxiaofa";
    private String password = "123456";
    private String token = "zhangxiaofa12345567testtoken";
}
