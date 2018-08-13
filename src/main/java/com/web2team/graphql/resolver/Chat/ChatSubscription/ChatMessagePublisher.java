package com.web2team.graphql.resolver.Chat.ChatSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Chat.Chat;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ChatMessagePublisher {
  private final RxBus<Chat> chatRxBus;

  @Autowired
  public ChatMessagePublisher(RxBus<Chat> chatRxBus) {
    this.chatRxBus = chatRxBus;
  }

  Flowable<Chat> getPublisher(Long chatThreadId) {
    return chatRxBus
        .toObservable()
        .filter(chat -> chat.getChatThread().getId().equals(chatThreadId))
        .toFlowable(BackpressureStrategy.BUFFER);
  }
}
