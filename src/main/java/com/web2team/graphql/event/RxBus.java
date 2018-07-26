package com.web2team.graphql.event;

import com.web2team.graphql.model.User;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import org.springframework.stereotype.Component;

@Component
public class RxBus {
  public RxBus() {}

  private PublishSubject<User> bus = PublishSubject.create();

  public void send(User user) {
    System.out.println("on rx send");
    bus.onNext(user);
  }

  public Observable<User> toObservable() {
    return bus;
  }

  public Flowable<User> toFlowable(BackpressureStrategy buffer) {
    return bus.toFlowable(buffer);
  }
}
