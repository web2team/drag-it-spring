package com.web2team.graphql.resolver.Chat.ChatSubscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.web2team.graphql.model.Chat.Chat;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ChatMessageUpdate implements GraphQLSubscriptionResolver {

  private ChatMessagePublisher chatMessagePublisher;

  @Autowired
  public ChatMessageUpdate(ChatMessagePublisher chatMessagePublisher) {
    this.chatMessagePublisher = chatMessagePublisher;
  }

  Publisher<Chat> linkChatMessage(Long chatThreadId) {
    return chatMessagePublisher.getPublisher(chatThreadId);
  }
}
