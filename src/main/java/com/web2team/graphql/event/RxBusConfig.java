package com.web2team.graphql.event;

import com.web2team.graphql.model.Chat.Chat;
import com.web2team.graphql.model.Grid.GridLayoutItem;
import com.web2team.graphql.model.Notification.Notification;
import com.web2team.graphql.model.User.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RxBusConfig {

  @Bean
  public RxBus<User> userRxBus() {
    return new RxBus<>();
  }

  @Bean
  public RxBus<Chat> chatRxBus() {
    return new RxBus<>();
  }

  @Bean
  public RxBus<GridLayoutItem> gridLayoutItemRxBus() {
    return new RxBus<>();
  }

  @Bean
  public RxBus<Notification> notificationRxBus() {
    return new RxBus<>();
  }
}
