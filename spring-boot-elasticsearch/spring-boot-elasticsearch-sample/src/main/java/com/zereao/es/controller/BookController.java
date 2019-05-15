package com.zereao.es.controller;

import com.zereao.es.pojo.vo.BookVO;
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
        return bookService.findBookById(id);
    }

    @PostMapping("add")
    public String add(@RequestBody BookVO vo) {
        return bookService.addBook(vo);
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") String id) {
        return bookService.delete(id);
    }

    @PostMapping("update")
    public String update(@RequestBody BookVO vo) {
        return bookService.update(vo);
    }

    @PostMapping("bool")
    public String boolQuery(@RequestBody BoolQueryVO vo) {
        return bookService.boolQuery(vo);
    }
}
