package com.web2team.graphql.resolver.Notification.NotificationSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Notification.Notification;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationPublisher {
  private RxBus<Notification> notificationRxBus;

  Flowable<Notification> getPublisher(Long userId) {
    return notificationRxBus
        .toObservable()
        .filter(notification -> notification.getUser().getId().equals(userId))
        .toFlowable(BackpressureStrategy.BUFFER);
  }
}
