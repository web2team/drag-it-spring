package com.web2team.graphql.repository.MapUserChatThread;

import com.web2team.graphql.model.MapUserChatThread.MapUserChatThread;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MapUserChatThreadRepository extends CrudRepository<MapUserChatThread, Long> {
  Optional<MapUserChatThread> findByUserIdEqualsAndChatThreadIdEquals(
      Long userId, Long chatThreadId);
}
