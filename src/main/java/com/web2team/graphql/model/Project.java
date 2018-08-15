package com.web2team.graphql.model;

import com.web2team.graphql.model.User.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Data
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private LocalDateTime createdAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "map_user_project",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
