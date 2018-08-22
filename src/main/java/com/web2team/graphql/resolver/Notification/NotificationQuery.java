package com.web2team.graphql.resolver.Notification;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Notification.Notification;
import com.web2team.graphql.repository.Notification.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NotificationQuery implements GraphQLQueryResolver {
  private NotificationRepository notificationRepository;

  public List<Notification> getNotifications(Long userId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "createdAt"));

    return notificationRepository.getAllByUserIdEquals(userId, pageable).getContent();
  }
}

