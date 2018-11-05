package com.web2team.graphql.repository.GridLayout.utility;

import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Grid.GridLayoutItemPropsInput;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import com.web2team.graphql.repository.Chat.ChatThreadRepository;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutItemPropsUtility {
  private ChatThreadRepository chatThreadRepository;
  private MapUserChatThreadRepository mapUserChatThreadRepository;

  public GridLayoutItemProps generateGridLayoutItemProps(
      GridLayoutItemPropsInput gridLayoutItemPropsInput) {
    GridLayoutItemProps gridLayoutItemProps = new GridLayoutItemProps();

    GridLayoutItemType type = gridLayoutItemPropsInput.getType();
    gridLayoutItemProps.setType(type);

    if (type.equals(GridLayoutItemType.CHATTING)) {
      ChatThread chatThread = new ChatThread();
      chatThread.setUsers(gridLayoutItemPropsInput.getChatThreadInput().getUsers());

      ChatThread savedChatThread = chatThreadRepository.save(chatThread);
      gridLayoutItemProps.setChatThread(savedChatThread);

      return gridLayoutItemProps;
    }

    if (type.equals(GridLayoutItemType.CALENDAR)) {
      ChatThread chatThread = new ChatThread();
      chatThread.setUsers(gridLayoutItemPropsInput.getChatThreadInput().getUsers());

      ChatThread savedChatThread = chatThreadRepository.save(chatThread);
      gridLayoutItemProps.setChatThread(savedChatThread);

      return gridLayoutItemProps;
    }

    throw new RuntimeException("invalid gridLayoutItemType");
  }
}
