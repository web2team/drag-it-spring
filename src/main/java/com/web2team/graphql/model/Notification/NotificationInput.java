package com.web2team.graphql.model.Notification;

import com.web2team.graphql.model.Grid.GridLayoutItemPropsInput;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import lombok.Data;

@Data
public class NotificationInput {
  private String title;
  private String message;

  GridLayoutItemType gridLayoutItemType;
  GridLayoutItemPropsInput gridLayoutItemPropsInput;
}
