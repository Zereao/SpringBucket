package com.zereao.thymeleaf.service;

import com.zereao.thymeleaf.dao.MessageDAO;
import com.zereao.thymeleaf.vo.DailyReportVO;
import com.zereao.thymeleaf.vo.TypeDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  11:40
 */
@Service
public class ParseService {
    private final MessageDAO messageDAO;
    private final TemplateEngine templateEngine;

    @Autowired
    public ParseService(MessageDAO messageDAO, TemplateEngine templateEngine) {
        this.messageDAO = messageDAO;
        this.templateEngine = templateEngine;
    }

    public String parse(List<DailyReportVO> voList) {
        Context context = new Context();
        context.setVariable("dailyReportVOList", voList);
        return templateEngine.process("table", context);
    }

    public List<DailyReportVO> getParseData(Date start, Date end) {
        List<DailyReportVO> reportVOList = new ArrayList<>();
        List<String> systemList = messageDAO.findAllSystem(start, end);
        systemList.forEach(sys -> {
            List<TypeDetailVO> messageList = messageDAO.findCountBySystem(sys, start, end);
            DailyReportVO reportVO = DailyReportVO.builder().system(sys).systemName(sys + "的名称").detailList(messageList).build();
            reportVOList.add(reportVO);
        });
        return reportVOList;
    }
}
