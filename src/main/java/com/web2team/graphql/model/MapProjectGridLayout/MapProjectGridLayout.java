package com.web2team.graphql.model.MapProjectGridLayout;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Data
@Entity
@IdClass(MapProjectGridLayoutId.class)
public class MapProjectGridLayout implements Serializable {
  @Id
  @Column(name = "project_id")
  Long projectId;

  @Id
  @Column(name = "grid_layout_id")
  Long gridLayoutId;
}
