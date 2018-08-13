package com.web2team.graphql.model.Grid;

import com.web2team.graphql.model.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grid {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn
  private User user;
}
