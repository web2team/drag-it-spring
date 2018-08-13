package com.web2team.graphql.model.Grid;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GridDraggableLayout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long gridId;

  @OneToOne @JoinColumn private GridData gridData;

  @Enumerated(EnumType.STRING)
  private GridComponentType gridComponentType;

  @OneToOne @JoinColumn private GridComponentProps gridComponentProps;
}
