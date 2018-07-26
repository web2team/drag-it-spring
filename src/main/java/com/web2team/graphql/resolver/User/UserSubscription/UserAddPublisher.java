package com.web2team.graphql.resolver.User.UserSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.User;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddPublisher {

  private final RxBus rxBus;

  @Autowired
  public UserAddPublisher(RxBus rxBus) {
    this.rxBus = rxBus;
  }

  public RxBus getBus() {
    return this.rxBus;
  }

  public Flowable<User> getPublisher() {
    return rxBus.toFlowable(BackpressureStrategy.BUFFER);
  }
}
