package com.web2team.graphql.model.Chat;

import com.web2team.graphql.model.User.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private User user;

  private String contents;

  private LocalDateTime createdAt;

  @ManyToOne private ChatThread chatThread;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
