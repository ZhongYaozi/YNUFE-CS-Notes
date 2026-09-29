package com.czx.demoMP1234.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String role;
    private Boolean status;
}