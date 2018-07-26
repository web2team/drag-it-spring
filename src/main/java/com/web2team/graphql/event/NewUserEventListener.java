package com.web2team.graphql.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserEventListener implements ApplicationListener<NewUserEvent> {
  @Override
  public void onApplicationEvent(NewUserEvent event) {
    event.getUser();
  }
}
