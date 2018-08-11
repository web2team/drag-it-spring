package com.web2team.graphql.repository.Grid;

import com.web2team.graphql.model.Grid.GridDraggableProps;
import org.springframework.data.repository.CrudRepository;

public interface GridDraggablePropsRepository extends CrudRepository<GridDraggableProps, Long> {
  Iterable<GridDraggableProps> getAllByGridIdEquals(Long gridId);

  GridDraggableProps findByGridIdEqualsAndIdEquals(Long grid_id, Long gridDraggableProps_id);
}
