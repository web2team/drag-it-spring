package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Chat.Chat;
import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.Chat.ChatRepository;
import com.web2team.graphql.repository.Chat.ChatThreadRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class ChatMutation implements GraphQLMutationResolver {
  private ChatRepository chatRepository;
  private ChatThreadRepository chatThreadRepository;
  private UserRepository userRepository;

  private RxBus<Chat> chatRxBus;

  public Chat newChatMessage(Long chatThreadId, Long userId, String contents) {
    Chat newChatMessage = new Chat();
    ChatThread chatThread =
        chatThreadRepository
            .findById(chatThreadId)
            .orElseThrow(() -> new NoSuchElementException("invalid chat thread id"));
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("invalid user id"));

    newChatMessage.setChatThread(chatThread);
    newChatMessage.setUser(user);
    newChatMessage.setContents(contents);

    Chat savedChatMessage = chatRepository.save(newChatMessage);
    chatRxBus.send(savedChatMessage);

    return savedChatMessage;
  }

  public ChatThread addUserToChatThread(Long userId, Long chatThreadId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("invalid userId"));
    ChatThread chatThread =
        chatThreadRepository
            .findById(chatThreadId)
            .orElseThrow(() -> new NoSuchElementException("invalid chatThreadId"));
    
    chatThread.getUsers().add(user);
    return chatThreadRepository.save(chatThread);
  }
}
