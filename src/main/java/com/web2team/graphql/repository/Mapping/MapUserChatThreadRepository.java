package com.web2team.graphql.repository.Mapping;

import com.web2team.graphql.model.Map.MapUserChatThread;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MapUserChatThreadRepository extends CrudRepository<MapUserChatThread, Long> {
  Optional<MapUserChatThread> findByUserIdEqualsAndChatThreadIdEquals(
      Long userId, Long chatThreadId);
}
