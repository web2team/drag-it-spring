package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Chat;
import com.web2team.graphql.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMutation implements GraphQLMutationResolver {
  private final ChatRepository chatRepository;
  private RxBus<Chat> chatRxBus;

  @Autowired
  public ChatMutation(ChatRepository chatRepository, RxBus<Chat> chatRxBus) {
    this.chatRepository = chatRepository;
    this.chatRxBus = chatRxBus;
  }

  public Chat newChatMessage(Long chat_thread_id, String username, String contents) {
    Chat newChatMessage = new Chat();
    newChatMessage.setChatThreadId(chat_thread_id);
    newChatMessage.setUsername(username);
    newChatMessage.setContents(contents);

    Chat savedChatMessage = chatRepository.save(newChatMessage);
    chatRxBus.send(savedChatMessage);

    return savedChatMessage;
  }
}
