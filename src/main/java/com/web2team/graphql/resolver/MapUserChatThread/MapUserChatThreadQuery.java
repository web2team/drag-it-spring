package com.web2team.graphql.resolver.MapUserChatThread;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.MapUserChatThread.MapUserChatThread;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
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
