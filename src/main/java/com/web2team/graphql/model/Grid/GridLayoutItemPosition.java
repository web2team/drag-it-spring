package com.web2team.graphql.model.Grid;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GridLayoutItemPosition {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long key;

  private Integer x = 0;
  private Integer y = 0;
  private Integer w = 8;
  private Integer h = 10;

  private Integer maxH;
  private Integer maxW;
  private Integer minH;
  private Integer minW;

  private Boolean isDraggable;
  private Boolean isResizable;

  @Column(name = "static")
  private Boolean isStatic;

  private String draggableHandle;
}
