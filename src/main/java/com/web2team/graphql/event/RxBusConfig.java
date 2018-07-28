package com.web2team.graphql.event;

import com.web2team.graphql.model.Chat;
import com.web2team.graphql.model.User;
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
}
