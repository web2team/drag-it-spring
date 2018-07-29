package com.web2team.graphql.repository;

import com.web2team.graphql.model.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
  Iterable<Chat> getAllByChatThreadIdEquals(Long chatThreadId);
}
