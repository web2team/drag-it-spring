package com.web2team.graphql.resolver.Grid;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Grid.Grid;
import com.web2team.graphql.model.Grid.GridDraggableLayout;
import com.web2team.graphql.repository.Grid.GridDraggableLayoutRepository;
import com.web2team.graphql.repository.Grid.GridRepository;
import com.web2team.graphql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class GridQuery implements GraphQLQueryResolver {
  private GridDraggableLayoutRepository gridDraggableLayoutRepository;
  private GridRepository gridRepository;

  public Iterable<GridDraggableLayout> getGridDraggableProps(Long gridId) {
    return gridDraggableLayoutRepository.getAllByGridIdEquals(gridId);
  }

  public Iterable<Grid> getGrids(Long userId) {
    return gridRepository.getAllByUserIdEquals(userId);
  }
}
