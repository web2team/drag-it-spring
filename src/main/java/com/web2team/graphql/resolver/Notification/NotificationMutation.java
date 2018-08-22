package com.web2team.graphql.resolver.Notification;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Notification.Notification;
import com.web2team.graphql.model.Notification.NotificationInput;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.Notification.NotificationRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class NotificationMutation implements GraphQLMutationResolver {
  private NotificationRepository notificationRepository;
  private UserRepository userRepository;
  private RxBus<Notification> notificationRxBus;

  public Notification newNotification(Long userId, NotificationInput input) {
    Notification toSave = new Notification();
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("invalid userId"));

    toSave.setUser(user);
    toSave.setTitle(input.getTitle());
    toSave.setMessage(input.getMessage());
    toSave.setGridLayoutItemType(input.getGridLayoutItemType());
    GridLayoutItemProps props = (GridLayoutItemProps) input.getGridLayoutItemPropsInput();
    toSave.setGridLayoutItemProps(props);

    Notification saved = notificationRepository.save(toSave);
    notificationRxBus.send(saved);
    return saved;
  }
}
