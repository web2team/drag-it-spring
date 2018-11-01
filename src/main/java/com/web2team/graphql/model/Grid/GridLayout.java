package com.web2team.graphql.model.Grid;

import com.web2team.graphql.model.Project;
import com.web2team.graphql.model.User.User;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GridLayout {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "gridLayout")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<GridLayoutItem> gridLayoutItems;

  @ManyToOne
  @JoinTable(
          name = "map_project_grid_layout",
          joinColumns = @JoinColumn(name = "grid_layout_id"),
          inverseJoinColumns = @JoinColumn(name = "project_id"))
  private Project project;

  @ManyToOne private User user;
}
