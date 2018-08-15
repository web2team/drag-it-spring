package com.web2team.graphql.repository.GridLayout;

import com.web2team.graphql.model.Grid.GridLayout;
import org.springframework.data.repository.CrudRepository;

public interface GridLayoutRepository extends CrudRepository<GridLayout, Long> {
  Iterable<GridLayout> getAllByUserId(Long userId);
}
