package com.web2team.graphql.model.Grid;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GridData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "_key")
  private String key;
  private Integer x;
  private Integer y;
  private Integer w;
  private Integer h;

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
