package com.zereao.thymeleaf.controller;

import com.zereao.thymeleaf.service.ParseService;
import com.zereao.thymeleaf.vo.DailyReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Darion Mograine H
 * @version 2019/03/25  19:55
 */
@Controller
public class DailyReportController {

    private final ParseService parseService;

    @Autowired
    public DailyReportController(ParseService parseService) {this.parseService = parseService;}

    @GetMapping("simple")
    @ResponseBody
    public String sendSimpleReport() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = format.parse("2018-05-05 00:00:00");
        Date end = format.parse("2018-05-25 00:00:00");
        List<DailyReportVO> voList = parseService.getParseData(start, end);
        return parseService.parse(voList);
    }
}
