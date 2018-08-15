package com.web2team.graphql.model.User;

import lombok.Data;

@Data
public class UserInput {
  private Long id;

  private String email;

  private String name;
}
