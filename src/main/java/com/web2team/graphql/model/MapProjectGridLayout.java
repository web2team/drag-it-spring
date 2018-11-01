package com.web2team.graphql.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class MapProjectGridLayout {
  @Id
  @Column(name = "project_id")
  Long projectId;

  @Id
  @Column(name = "grid_layout_id")
  Long gridLayoutId;
}
