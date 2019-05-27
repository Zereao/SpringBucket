package com.zereao.es.dao;

import com.zereao.es.pojo.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Zereao
 * @version 2019/05/15 14:51
 */
@Repository
public interface BookDAO extends ElasticsearchRepository<Book, String> {

}
