package com.web2team.graphql.resolver.User.UserSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.User.User;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class UserAddPublisher {
  private final RxBus<User> userRxBus;

  @Autowired
  public UserAddPublisher(RxBus<User> userRxBus) {
    this.userRxBus = userRxBus;
  }

  public RxBus getBus() {
    return this.userRxBus;
  }

  Flowable<User> getPublisher() {
    return userRxBus.toFlowable(BackpressureStrategy.BUFFER);
  }
}
