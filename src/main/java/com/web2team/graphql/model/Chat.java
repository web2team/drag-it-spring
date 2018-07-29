package com.web2team.graphql.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  private String contents;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(name = "chat_thread_id")
  private Long chatThreadId;

  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }
}
