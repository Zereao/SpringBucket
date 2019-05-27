package com.zereao.es.service;

import com.zereao.es.dao.BookDAO;
import com.zereao.es.pojo.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zereao
 * @version 2019/05/14 15:09
 */
@Service
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public Book getById(String id) {
        return bookDAO.findById(id).orElse(null);
    }
}
