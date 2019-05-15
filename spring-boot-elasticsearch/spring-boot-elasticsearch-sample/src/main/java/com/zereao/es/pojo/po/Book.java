package com.zereao.es.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Zereao
 * @version 2019/05/14 15:15
 */
@Data
public class Book {
    private String id;
    /**
     * 文章类型
     */
    private String type;
    /**
     * 文章字数
     */
    @JsonProperty("word_count")
    private Integer wordCount;
    /**
     * 作者
     */
    private String author;
    /**
     * 标题
     */
    private String title;
    /**
     * 出版日期
     */
    @JsonProperty("publish_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
}
