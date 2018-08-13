package com.web2team.graphql.repository.Chat;

import com.web2team.graphql.model.Chat.Chat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long> {
  Iterable<Chat> getAllByChatThreadIdEquals(Long chatThreadId);

  Page<Chat> getAllByChatThreadIdEquals(Long chatThreadId, Pageable pageable);
}
