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

  @ManyToOne
  private ChatThread chatThread;
}
