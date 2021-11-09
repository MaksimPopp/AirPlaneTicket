package com.company.demo.controller.request;

import com.company.demo.models.Plane;
import com.company.demo.models.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class UpdateTicketRequest {
    @ManyToOne
    private Plane plane;
    @ManyToOne
    private User user;
    @NotEmpty
    private BigDecimal price;
}
