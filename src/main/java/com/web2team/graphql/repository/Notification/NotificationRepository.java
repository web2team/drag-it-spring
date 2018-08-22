package com.web2team.graphql.repository.Notification;

import com.web2team.graphql.model.Notification.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Long> {
  Page<Notification> getAllByUserIdEquals(Long userId, Pageable pageable);
}
