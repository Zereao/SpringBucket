package com.zereao.freemarker.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Darion Mograine H
 * @version 2019/03/25  20:20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportVO {
    private String system;
    private String systemName;
    private List<TypeDetailVO> detailList;
}
