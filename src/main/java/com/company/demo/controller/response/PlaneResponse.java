package com.company.demo.controller.response;

import com.company.demo.models.Ticket;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
public class PlaneResponse {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer places;
    private LocalDate depart;
    private Duration duration;
    @NotEmpty
    private String departFrom;
    @NotEmpty
    private String arriveTo;
    private List<TicketShortInfo> tickets;
    private boolean isDeleted;
}
