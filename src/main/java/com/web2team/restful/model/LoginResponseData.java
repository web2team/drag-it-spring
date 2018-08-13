package com.web2team.restful.model;

import lombok.Data;

@Data
public class LoginResponseData {
  private String userName;
  private Long userId;

  private String token;
  private int time;
}
