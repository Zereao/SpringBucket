package com.zereao.es.pojo.vo;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/05/14 19:03
 */
@Data
public class BookVO {
    private String id;
    private String type;
    private Integer wordCount;
    private String author;
    private String title;
    private String publishDate;
}
