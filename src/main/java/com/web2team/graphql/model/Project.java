package com.web2team.graphql.model;

import com.web2team.graphql.model.Grid.GridLayout;
import com.web2team.graphql.model.User.User;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

  private String name = "새 프로젝트";

  private LocalDateTime createdAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "map_user_project",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;

  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(
      name = "map_project_grid_layout",
      joinColumns = @JoinColumn(name = "project_id"),
      inverseJoinColumns = @JoinColumn(name = "grid_layout_id"))
  private List<GridLayout> gridLayouts;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now(ZoneId.systemDefault());
  }
}
