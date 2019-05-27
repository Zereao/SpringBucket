package com.zereao.es.pojo.vo;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/05/14 19:14
 */
@Data
public class BoolQueryVO {
    private String author;
    private String title;
    private Integer gtWordCount;
    private Integer ltWordCount;
}
