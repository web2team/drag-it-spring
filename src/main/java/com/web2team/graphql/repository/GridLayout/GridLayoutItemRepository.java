package com.web2team.graphql.repository.GridLayout;

import com.web2team.graphql.model.Grid.GridLayoutItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GridLayoutItemRepository extends CrudRepository<GridLayoutItem, Long> {
  Iterable<GridLayoutItem> getAllByGridLayoutId(Long gridLayoutId);

  Optional<GridLayoutItem> findByGridLayoutIdEqualsAndIdEquals(
      Long gridLayoutId, Long gridLayoutItemPropsId);
}
