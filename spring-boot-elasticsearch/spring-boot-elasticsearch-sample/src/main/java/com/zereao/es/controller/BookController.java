package com.zereao.es.controller;

import com.zereao.es.pojo.vo.BookAddVO;
import com.zereao.es.pojo.vo.BookUpdateVO;
import com.zereao.es.pojo.vo.BoolQueryVO;
import com.zereao.es.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Zereao
 * @version 2019/04/29 14:46
 */
@RestController
@RequestMapping("book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("get/{id}")
    public String get(@PathVariable("id") String id) {
        return bookService.findBookById(id).toString();
    }

    @PostMapping("add")
    public String add(@RequestBody BookAddVO vo) {
        bookService.addBook(vo);
        return "SUCCESS";
    }

    @GetMapping("delete")
    public String delete() {
        bookService.delete();
        return "SUCCESS";
    }

    @PostMapping("update")
    public String update(@RequestBody BookUpdateVO vo) {
        bookService.update(vo);
        return "SUCCESS";
    }

    @PostMapping("bool")
    public String boolQuery(@RequestBody BoolQueryVO vo) {
        return bookService.boolQuery(vo);
    }

    public String terms() {
        return bookService.boolQuery(vo);
    }
}
