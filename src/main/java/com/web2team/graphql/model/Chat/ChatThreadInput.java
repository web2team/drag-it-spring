package com.web2team.graphql.model.Chat;

import com.web2team.graphql.model.User.User;
import lombok.Data;

import java.util.List;

@Data
public class ChatThreadInput {
  private String threadName;

  private List<User> users;
}
