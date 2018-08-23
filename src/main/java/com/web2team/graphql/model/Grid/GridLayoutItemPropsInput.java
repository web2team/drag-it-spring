package com.web2team.graphql.model.Grid;

import com.web2team.graphql.model.Chat.ChatThreadInput;
import lombok.Data;

@Data
public class GridLayoutItemPropsInput {
  private GridLayoutItemType type;

  private ChatThreadInput chatThreadInput;
}
