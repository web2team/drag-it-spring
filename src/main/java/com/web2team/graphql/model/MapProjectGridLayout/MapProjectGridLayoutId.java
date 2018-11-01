package com.web2team.graphql.model.MapProjectGridLayout;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapProjectGridLayoutId implements Serializable {
  private static final long serialVersionUID = -3862883518656420750L;
  Long projectId;

  Long gridLayoutId;
}
