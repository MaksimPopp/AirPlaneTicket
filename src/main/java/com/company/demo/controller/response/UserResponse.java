package com.company.demo.controller.response;

import com.company.demo.models.Ticket;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
public class UserResponse {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String passport;
    @OneToMany(mappedBy = "user")
    private List<TicketShortInfo> tickets;
    private boolean isDeleted;
}
