package com.web2team.graphql.repository.GridLayout.utility;

import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Grid.GridLayoutItemPropsInput;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import com.web2team.graphql.repository.Chat.ChatThreadRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutItemPropsUtility {

  private ChatThreadRepository chatThreadRepository;

  public GridLayoutItemProps generateGridLayoutItemProps(
      GridLayoutItemType gridLayoutItemType, GridLayoutItemPropsInput gridLayoutItemPropsInput) {
    GridLayoutItemProps gridLayoutItemProps = new GridLayoutItemProps();

    if (gridLayoutItemType.equals(GridLayoutItemType.CHATTING)) {
      ChatThread chatThread = new ChatThread();
      chatThread.setUsers(gridLayoutItemPropsInput.getChatThreadInput().getUsers());
      ChatThread savedChatThread = chatThreadRepository.save(chatThread);

      gridLayoutItemProps.setChatThread(savedChatThread);
      return gridLayoutItemProps;
    }

    throw new RuntimeException("invalid gridLayoutItemType");
  }
}
