package com.web2team.restful.model;

import lombok.Data;

@Data
public class RegisterReqeustBody {
  private String email;
  private String password;
  private String userName;
  private String phone;
}
