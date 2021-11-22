package com.company.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Plane {
    @Id
    @GeneratedValue
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
    @OneToMany(mappedBy = "plane")
    private List<Ticket> tickets;
    private boolean isDeleted;
}
