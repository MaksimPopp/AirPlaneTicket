package com.company.demo.controller.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class TicketResponse {
    private Long id;
    private PlaneShortInfoResponse plane;
    private UserShortInfoResponse user;
    private BigDecimal price;
    private boolean isDeleted;
}
