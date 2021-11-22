package com.company.demo.controller.request;

import com.company.demo.models.Ticket;
import lombok.Data;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.lang.NonNull;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreatePlaneRequest {
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
}
