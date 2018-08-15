package com.web2team.graphql.model.MapUserChatThread;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapUserChatThreadId implements Serializable {
  private static final long serialVersionUID = -3862883518656420782L;

  private Long userId;
  private Long chatThreadId;
}
