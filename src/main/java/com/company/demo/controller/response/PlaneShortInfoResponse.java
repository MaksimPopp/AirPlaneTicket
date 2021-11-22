package com.company.demo.controller.response;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;

@Data
public class PlaneShortInfoResponse {
    private Long id;
    private String name;
    private Integer places;
    private LocalDate depart;
    private Duration duration;
    private String departFrom;
    private String arriveTo;
    private boolean isDeleted;
}
