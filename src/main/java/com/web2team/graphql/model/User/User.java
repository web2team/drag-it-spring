package com.web2team.graphql.model.User;

import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.Grid.GridLayout;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Data
public class User extends UserInput {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;
  private String password;

  private String name;

  private String phone;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @OneToMany(mappedBy = "user")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<GridLayout> gridLayouts;

  @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<ChatThread> chatThreads;

  @PrePersist
  protected void onCreate() {
    this.updatedAt = this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
