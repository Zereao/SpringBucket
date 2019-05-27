package com.zereao.es.controller;

import com.zereao.es.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return bookService.getById(id).toString();
    }

//    @PostMapping("add")
//    public String add(@RequestBody BookAddVO vo) {
//        bookService.addBook(vo);
//        return "SUCCESS";
//    }
//
//    @GetMapping("delete")
//    public String delete() {
//        bookService.delete();
//        return "SUCCESS";
//    }
//
//    @PostMapping("update")
//    public String update(@RequestBody BookUpdateVO vo) {
//        bookService.update(vo);
//        return "SUCCESS";
//    }
//
//    @PostMapping("bool")
//    public String boolQuery(@RequestBody BoolQueryVO vo) {
//        return bookService.boolQuery(vo);
//    }
//
//    public String terms() {
//        return bookService.boolQuery(vo);
//    }
}
