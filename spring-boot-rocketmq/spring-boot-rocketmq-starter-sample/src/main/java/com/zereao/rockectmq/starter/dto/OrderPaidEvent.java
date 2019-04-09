package com.zereao.rockectmq.starter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Zereao
 * @version 2019/04/08 16:51
 */
@Data
@AllArgsConstructor
public class OrderPaidEvent {
    private String orderId;

    private BigDecimal paidMoney;
}
