package com.company.demo.controller.request;

import com.company.demo.models.Ticket;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateUserRequest {
    @Id
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String passport;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
    private boolean isDeleted;
}
