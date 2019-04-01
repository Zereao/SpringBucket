package com.zereao.freemarker.controller;

import com.zereao.freemarker.service.ParseService;
import com.zereao.freemarker.vo.DailyReportVO;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Zereao
 * @version 2019/04/01 10:24
 */
@RestController
@RequestMapping("test")
public class ReportController {
    @Autowired
    private ParseService parseService;

    @GetMapping("simple")
    public String sendSimpleReport() throws ParseException, IOException, TemplateException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = format.parse("2018-05-05 00:00:00");
        Date end = format.parse("2018-05-25 00:00:00");
        List<DailyReportVO> voList = parseService.getParseData(start, end);
        return parseService.parse(voList);
    }
}
