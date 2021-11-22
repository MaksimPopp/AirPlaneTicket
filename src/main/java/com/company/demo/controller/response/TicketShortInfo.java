package com.company.demo.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketShortInfo {
    private Long id;
    private BigDecimal price;
    private boolean isDeleted;
}
