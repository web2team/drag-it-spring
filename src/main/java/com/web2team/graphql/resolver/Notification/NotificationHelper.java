package com.web2team.graphql.resolver.Notification;

import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import com.web2team.graphql.model.Notification.Notification;
import com.web2team.graphql.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationHelper {
  private NotificationMutation notificationMutation;

  @Autowired
  public NotificationHelper(@Lazy NotificationMutation notificationMutation) {
    this.notificationMutation = notificationMutation;
  }

  public void publishNotification(
      List<User> to, User from, GridLayoutItemType type, GridLayoutItemProps props) {
    for (User user : to) {
      Notification noti = new Notification();
      noti.setTitle("새로운 초대 알림");
      noti.setMessage(from.getName() + "님께서 새로운 채팅방에 초대하셨습니다");
      noti.setGridLayoutItemType(type);
      noti.setGridLayoutItemProps(props);

      notificationMutation.newNotification(user.getId(), noti);
    }
  }
}
