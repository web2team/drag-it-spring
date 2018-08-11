package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.web2team.graphql.model.Chat;
import com.web2team.graphql.repository.ChatRepository;
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

  public Iterable<Chat> getAllMessages(Long chat_thread_id, DataFetchingEnvironment env) {
    return chatRepository.getAllByChatThreadIdEquals(chat_thread_id);
  }

  public List<Chat> getMessages(Long chat_thread_id, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, new Sort(Sort.Direction.DESC, "createdAt"));

    return Lists.reverse(
        chatRepository.getAllByChatThreadIdEquals(chat_thread_id, pageable).getContent());
  } 
}
