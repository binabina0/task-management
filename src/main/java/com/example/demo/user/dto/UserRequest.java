package com.example.demo.user.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
    private String name;

}
