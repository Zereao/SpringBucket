package com.zereao.freemarker.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  10:09
 */
@Data
@Entity
@AllArgsConstructor
@Table(name = "s_sms_message_2018_05")
public class TypeDetailVO {
    @Id
    private String sentType;
    private Long sendTotal;
    private Long successNum;
    private Long failedNum;
    private Long unknownNum;
    private Long delay10Sec;
    private Long delay30Sec;
    private Long delay60Sec;
    private Long delay10Min;
    private Long delayOver10Min;
}
