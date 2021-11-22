package com.company.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Plane plane;
    @ManyToOne
    private User user;
    @NotEmpty
    private BigDecimal price;
    private boolean isDeleted;
}
