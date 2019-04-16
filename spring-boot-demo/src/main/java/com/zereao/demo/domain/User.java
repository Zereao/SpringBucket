package com.zereao.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zereao
 * @version 2019/04/16 11:36
 */
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
}
