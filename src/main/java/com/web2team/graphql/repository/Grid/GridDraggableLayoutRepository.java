package com.web2team.graphql.repository.Grid;

import com.web2team.graphql.model.Grid.GridDraggableLayout;
import org.springframework.data.repository.CrudRepository;

public interface GridDraggableLayoutRepository extends CrudRepository<GridDraggableLayout, Long> {
  Iterable<GridDraggableLayout> getAllByGridIdEquals(Long grid_id);

  GridDraggableLayout findByGridIdEqualsAndIdEquals(Long grid_id, Long gridDraggableProps_id);
}
