package com.web2team.graphql.model.Chat;

import com.web2team.graphql.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private User user;

  private String contents;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @ManyToOne
  private ChatThread chatThread;

  @PrePersist
  protected void onCreate() {
    this.createdAt = new Date();
  }
}
