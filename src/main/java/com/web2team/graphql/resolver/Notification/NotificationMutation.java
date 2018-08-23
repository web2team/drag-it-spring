package com.web2team.graphql.resolver.Notification;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Notification.Notification;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.Notification.NotificationRepository;
import com.web2team.graphql.repository.User.UserRepository;
import com.web2team.graphql.resolver.GridLayout.GridLayoutItemHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationMutation implements GraphQLMutationResolver {
  private NotificationRepository notificationRepository;
  private UserRepository userRepository;
  private RxBus<Notification> notificationRxBus;
  private GridLayoutItemPropsRepository GLItemPropsRepo;
  private GridLayoutItemHelper GLItemHelper;

  public Notification newNotification(Long userId, Notification noti) {
    Notification toSave = new Notification();
    User user = userRepository.findById(userId).get();

    toSave.setUser(user);
    toSave.setTitle(noti.getTitle());
    toSave.setMessage(noti.getMessage());
    toSave.setGridLayoutItemType(noti.getGridLayoutItemType());
    toSave.setGridLayoutItemProps(noti.getGridLayoutItemProps());

    Notification saved = notificationRepository.save(toSave);
    notificationRxBus.send(saved);
    return saved;
  }

  public boolean confirmNotification() {
    return true;
  }

  public boolean acceptNotification(Long gridLayoutId, Long notiId) {
    Notification notification = notificationRepository.findById(notiId).get();
    notification.setIsRead(true);
    notificationRepository.save(notification);

    GridLayoutItemProps props = notification.getGridLayoutItemProps();
    GLItemHelper.generateNewGridLayoutItem(gridLayoutId, props);

    return true;
  }
}
