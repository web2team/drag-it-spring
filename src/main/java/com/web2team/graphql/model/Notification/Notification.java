package com.web2team.graphql.model.Notification;

import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.model.Grid.GridLayoutItemType;
import com.web2team.graphql.model.User.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
public class Notification {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String message;

  private Boolean isRead = false;

  private LocalDateTime createdAt;

  @ManyToOne private User user;

  @Enumerated(EnumType.STRING)
  private GridLayoutItemType gridLayoutItemType;

  @ManyToOne private GridLayoutItemProps gridLayoutItemProps;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
