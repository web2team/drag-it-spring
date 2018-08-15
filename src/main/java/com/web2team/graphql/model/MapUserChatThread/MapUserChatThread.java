package com.web2team.graphql.model.MapUserChatThread;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@IdClass(MapUserChatThreadId.class)
public class MapUserChatThread implements Serializable {
  @Id
  @Column(name = "user_id")
  private Long userId;

  @Id
  @Column(name = "chat_thread_id")
  private Long chatThreadId;

  @Column
  private String name = "newChat";
}
