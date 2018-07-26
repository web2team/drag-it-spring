package com.web2team.graphql.event;

import com.web2team.graphql.model.User;
import org.springframework.context.ApplicationEvent;

public class NewUserEvent extends ApplicationEvent {
  private User user;

  public NewUserEvent(Object source, User user) {
    super(source);
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
