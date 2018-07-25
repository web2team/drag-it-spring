package com.web2team.restful.model;

import lombok.Data;

@Data
public class LoginRequestBody {
  private String email;
  private String password;
}
