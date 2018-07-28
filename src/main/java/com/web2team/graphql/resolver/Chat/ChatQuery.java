package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Chat;
import com.web2team.graphql.repository.ChatRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChatQuery implements GraphQLQueryResolver {
  private ChatRepository chatRepository;

  public Iterable<Chat> getAllMessages(Long chat_thread_id, DataFetchingEnvironment env) {
    return chatRepository.getAllByChatThreadIdEquals(chat_thread_id);
  }
}
