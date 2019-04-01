package com.zereao.freemarker.service;

import com.zereao.freemarker.dao.MessageDAO;
import com.zereao.freemarker.vo.DailyReportVO;
import com.zereao.freemarker.vo.TypeDetailVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  11:40
 */
@Service
public class ParseService {
    private final MessageDAO messageDAO;
    private final Configuration configuration;

    @Autowired
    public ParseService(MessageDAO messageDAO, Configuration configuration) {
        this.messageDAO = messageDAO;
        this.configuration = configuration;
    }

    public String parse(List<DailyReportVO> voList) throws IOException, TemplateException {
        Template template = configuration.getTemplate("table.ftl");
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("dailyReportVOList", voList);
        template.process(model, writer);
        return writer.getBuffer().toString();
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
