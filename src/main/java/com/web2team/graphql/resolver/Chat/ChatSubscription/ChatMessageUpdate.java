package com.web2team.graphql.resolver.Chat.ChatSubscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.web2team.graphql.model.Chat.Chat;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class ChatMessageUpdate implements GraphQLSubscriptionResolver {

  private ChatMessagePublisher chatMessagePublisher;

  Publisher<Chat> linkChatMessage(Long chatThreadId) {
    return chatMessagePublisher.getPublisher(chatThreadId);
  }
}
