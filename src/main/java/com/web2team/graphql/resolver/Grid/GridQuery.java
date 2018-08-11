package com.web2team.graphql.resolver.Grid;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Grid.GridDraggableProps;
import com.web2team.graphql.repository.Grid.GridDraggablePropsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridQuery implements GraphQLQueryResolver {
  private GridDraggablePropsRepository gridRepository;

  public Iterable<GridDraggableProps> getGridDraggableProps(Long grid_id) {
    return gridRepository.getAllByGridIdEquals(grid_id);
  }
}
