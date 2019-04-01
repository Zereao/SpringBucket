package com.zereao.freemarker.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Darion Mograine H
 * @version 2019/03/26  09:53
 */
@Data
@Entity
@Table(name = "s_sms_message_2018_05")
public class Message {
    @Id
    private long id;
    private String delayFlag;
    private int delayTime;
    private Date createTime;
    private Date updateTime;
    private String expandtCode;
    private String messageNumber;
    private String message;
    private String mobile;
    private String productSystem;
    private String category1;
    private String category2;
    private String category3;
    private String category4;
    private String category5;
    private int partnerId;
    private String sentType;
    private int quantity;
    private String operator;
    private String statusCode;
    private String sendMarking;
    private long smsGroupId;
    private String errorDescription;
    private String msgId;
    private String responseReportStatus;
    private String responseErrorMsg;
    private Date responseSendTime;
    private String tplNumber;
}
