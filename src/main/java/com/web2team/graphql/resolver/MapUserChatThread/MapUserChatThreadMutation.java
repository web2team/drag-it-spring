package com.web2team.graphql.resolver.MapUserChatThread;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.MapUserChatThread.MapUserChatThread;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class MapUserChatThreadMutation implements GraphQLMutationResolver {
  private MapUserChatThreadRepository mapUserChatThreadRepository;

//  public MapUserChatThread addUserToChatThread(Long userId, Long chatThreadId) {
//    MapUserChatThread mapUserChatThread = new MapUserChatThread();
//
//    mapUserChatThread.setUserId(userId);
//    mapUserChatThread.setChatThreadId(chatThreadId);
//
//    return mapUserChatThreadRepository.save(mapUserChatThread);
//  }

  public MapUserChatThread updateUserChatThreadName(Long userId, Long chatThreadId, String name) {
    MapUserChatThread mapUserChatThread =
        mapUserChatThreadRepository
            .findByUserIdEqualsAndChatThreadIdEquals(userId, chatThreadId)
            .orElseThrow(() -> new NoSuchElementException("invalid userId, chatThreadId"));

    mapUserChatThread.setName(name);

    return mapUserChatThreadRepository.save(mapUserChatThread);
  }
}
