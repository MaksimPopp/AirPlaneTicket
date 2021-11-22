package com.company.demo.controller.response;

import lombok.Data;

@Data
public class UserShortInfoResponse {
    private Long id;
    private String firstname;
    private String lastname;
    private String passport;
    private boolean isDeleted;
}
