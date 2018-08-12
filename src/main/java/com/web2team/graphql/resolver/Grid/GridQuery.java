package com.web2team.graphql.resolver.Grid;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Grid.GridDraggableLayout;
import com.web2team.graphql.repository.Grid.GridDraggableLayoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridQuery implements GraphQLQueryResolver {
  private GridDraggableLayoutRepository gridRepository;

  public Iterable<GridDraggableLayout> getGridDraggableProps(Long grid_id) {
    return gridRepository.getAllByGridIdEquals(grid_id);
  }
}
