package com.web2team.graphql.model.User;

import com.web2team.graphql.model.Grid.GridLayout;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Data
public class User {
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

  @OneToMany
  @JoinColumn
  private List<GridLayout> gridLayouts;

  @PrePersist
  protected void onCreate() {
    this.updatedAt = this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
