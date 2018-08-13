package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.web2team.graphql.model.Chat.Chat;
import com.web2team.graphql.repository.Chat.ChatRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChatQuery implements GraphQLQueryResolver {
  private ChatRepository chatRepository;

  public Iterable<Chat> getAllChatMessages(Long chatThreadId, DataFetchingEnvironment env) {
    return chatRepository.getAllByChatThreadIdEquals(chatThreadId);
  }

  public List<Chat> getChatMessages(Long chatThreadId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "createdAt"));

    return Lists.reverse(
        chatRepository.getAllByChatThreadIdEquals(chatThreadId, pageable).getContent());
  } 
}
