package com.web2team.graphql.resolver.Chat.ChatSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Chat.Chat;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ChatMessagePublisher {
  private final RxBus<Chat> chatRxBus;

  Flowable<Chat> getPublisher(Long chatThreadId) {
    return chatRxBus
        .toObservable()
        .filter(chat -> chat.getChatThread().getId().equals(chatThreadId))
        .toFlowable(BackpressureStrategy.BUFFER);
  }
}
