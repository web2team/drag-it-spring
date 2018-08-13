package com.web2team.graphql.repository.Grid;

import com.web2team.graphql.model.Grid.GridDraggableLayout;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GridDraggableLayoutRepository extends CrudRepository<GridDraggableLayout, Long> {
  Iterable<GridDraggableLayout> getAllByGridIdEquals(Long gridId);

  Optional<GridDraggableLayout> findByGridIdEqualsAndIdEquals(
      Long gridId, Long gridDraggablePropsId);
}
