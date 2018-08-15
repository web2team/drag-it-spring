package com.web2team.graphql.resolver.Chat;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Chat.Chat;
import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.Grid.*;
import com.web2team.graphql.model.MapUserChatThread.MapUserChatThread;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.model.User.UserInput;
import com.web2team.graphql.repository.Chat.ChatRepository;
import com.web2team.graphql.repository.Chat.ChatThreadRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class ChatMutation implements GraphQLMutationResolver {
  private ChatRepository chatRepository;
  private ChatThreadRepository chatThreadRepository;
  private UserRepository userRepository;
  private MapUserChatThreadRepository mapUserChatThreadRepository;
  private GridLayoutItemRepository gridLayoutItemRepository;
  private GridLayoutRepository gridLayoutRepository;
  private GridLayoutItemPositionRepository gridLayoutItemPositionRepository;
  private GridLayoutItemPropsRepository gridLayoutItemPropsRepository;

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

//  public ChatThread newChatThread(Long gridLayoutId, List<UserInput> users, @Nullable String threadName) {
//    ChatThread chatThread = new ChatThread();
//    ChatThread savedChatThread = chatThreadRepository.save(chatThread);
//    Long threadId = savedChatThread.getId();
//
//    for (UserInput userInput : users) {
//      MapUserChatThread mapUserChatThread = new MapUserChatThread();
//      mapUserChatThread.setChatThreadId(threadId);
//      mapUserChatThread.setUserId(userInput.getId());
//      if (threadName != null) {
//        mapUserChatThread.setName(threadName);
//      }
//
//      mapUserChatThreadRepository.save(mapUserChatThread);
//    }
//
//    GridLayoutItemPosition gridLayoutItemPosition = new GridLayoutItemPosition();
//    GridLayoutItemProps gridLayoutItemProps = new GridLayoutItemProps();
//    gridLayoutItemProps.setChatThreadId(threadId);
//    GridLayout gridLayout =
//        gridLayoutRepository
//            .findById(gridLayoutId)
//            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId"));
//
//    GridLayoutItem gridLayoutItem = new GridLayoutItem();
//    gridLayoutItem.setGridLayoutItemPosition(gridLayoutItemPositionRepository.save(gridLayoutItemPosition));
//    gridLayoutItem.setGridLayoutItemProps(
//        gridLayoutItemPropsRepository.save(gridLayoutItemProps));
//    gridLayoutItem.setGridLayoutItemType(GridLayoutItemType.CHATTING);
//    gridLayoutItem.setGridLayout(gridLayoutRepository.save(gridLayout));
//    gridLayoutItemRepository.save(gridLayoutItem);
//
//    return chatThreadRepository
//        .findById(threadId)
//        .orElseThrow(() -> new RuntimeException("error on creating newChatThread"));
//  }
}
