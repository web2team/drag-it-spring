package com.web2team.graphql.resolver.Notification.NotificationSubscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.web2team.graphql.model.Notification.Notification;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationUpdate implements GraphQLSubscriptionResolver {
  private NotificationPublisher notificationPublisher;

  Publisher<Notification> linkNotification(Long userId) {
    return notificationPublisher.getPublisher(userId);
  }
}
