package com.web2team.graphql.model.Grid;

import com.web2team.graphql.model.Chat.ChatThread;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GridLayoutItemProps extends GridLayoutItemPropsInput {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private GridLayoutItemType type;

  @ManyToOne private ChatThread chatThread;
}
