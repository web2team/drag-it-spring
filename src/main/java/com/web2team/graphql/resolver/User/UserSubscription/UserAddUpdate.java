package com.web2team.graphql.resolver.User.UserSubscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.web2team.graphql.model.User.User;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
public class UserAddUpdate implements GraphQLSubscriptionResolver {

  private UserAddPublisher userAddPublisher;

  UserAddUpdate(UserAddPublisher userAddPublisher) {
    this.userAddPublisher = userAddPublisher;
  }

  Publisher<User> getUserRegisterList() {
    return userAddPublisher.getPublisher();
  }
}

