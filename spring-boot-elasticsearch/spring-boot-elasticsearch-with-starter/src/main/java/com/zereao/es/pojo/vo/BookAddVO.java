package com.zereao.es.pojo.vo;

import lombok.Data;

/**
 * @author Zereao
 * @version 2019/05/14 17:30
 */
@Data
public class BookAddVO {
    private String type;
    private Integer wordCount;
    private String author;
    private String title;
    private String publishDate;
}
