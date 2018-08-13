package com.web2team.graphql.resolver.Map;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Map.MapUserChatThread;
import com.web2team.graphql.repository.Mapping.MapUserChatThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class MapUserChatThreadQuery implements GraphQLQueryResolver {
  private MapUserChatThreadRepository mapUserChatThreadRepository;

  MapUserChatThread getMapUserChatThread(Long userId, Long chatThreadId) {
    return mapUserChatThreadRepository
        .findByUserIdEqualsAndChatThreadIdEquals(userId, chatThreadId)
        .orElseThrow(() -> new NoSuchElementException("invalid userId and chatThreadId"));
  }
}
