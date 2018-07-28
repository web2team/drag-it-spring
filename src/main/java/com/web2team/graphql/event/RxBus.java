package com.web2team.graphql.event;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus<T> {
  public RxBus() {}

  private PublishSubject<T> bus = PublishSubject.create();

  public void send(T data) {
    bus.onNext(data);
  }

  public Observable<T> toObservable() {
    return bus;
  }

  public Flowable<T> toFlowable(BackpressureStrategy buffer) {
    return bus.toFlowable(buffer);
  }
}
