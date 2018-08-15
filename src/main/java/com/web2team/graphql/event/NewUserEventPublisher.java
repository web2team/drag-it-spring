package com.web2team.graphql.event;

import com.web2team.graphql.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class NewUserEventPublisher {
  private final ApplicationEventPublisher applicationEventPublisher;

  @Autowired
  public NewUserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void doPublish(final User user) {
    System.out.println("user pulish");
    NewUserEvent userEvent = new NewUserEvent(this, user);

    applicationEventPublisher.publishEvent(userEvent);
  }
}
