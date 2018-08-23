package com.web2team.graphql.resolver.Notification;

import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import com.web2team.graphql.model.Notification.NotificationInput;
import com.web2team.graphql.model.User.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NotificationHelper {
  private NotificationMutation notificationMutation;

  public void publishNotification(
      List<User> to, User from, GridLayoutItemType type, GridLayoutItemProps props) {
    for (User user : to) {
      NotificationInput input = new NotificationInput();
      input.setTitle("새로운 초대 알림");
      input.setMessage(from.getName() + "님께서 새로운 채팅방에 초대하셨습니다");
      input.setGridLayoutItemType(type);
      input.setGridLayoutItemPropsInput(props);

      notificationMutation.newNotification(user.getId(), input);
    }
  }
}
