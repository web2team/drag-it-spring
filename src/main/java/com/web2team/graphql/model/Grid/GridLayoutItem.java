package com.web2team.graphql.model.Grid;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GridLayoutItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private GridLayout gridLayout;

  @Enumerated(EnumType.STRING)
  private GridLayoutItemType gridLayoutItemType;

  @OneToOne private GridLayoutItemProps gridLayoutItemProps;

  @OneToOne private GridLayoutItemPosition gridLayoutItemPosition;
}
