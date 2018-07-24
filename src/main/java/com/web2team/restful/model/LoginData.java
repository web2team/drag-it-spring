package com.web2team.restful.model;

import lombok.Data;

@Data
public class LoginData {
    private String name;
    private String token;
    private int time;
}
