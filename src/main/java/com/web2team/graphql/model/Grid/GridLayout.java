package com.web2team.graphql.model.Grid;

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

  @ManyToOne private User user;
}
