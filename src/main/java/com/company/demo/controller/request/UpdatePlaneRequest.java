package com.company.demo.controller.request;

import com.company.demo.models.Ticket;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
public class UpdatePlaneRequest {
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
    @OneToMany(mappedBy = "plane")
    private List<Ticket> tickets;
}
