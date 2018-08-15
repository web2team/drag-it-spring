package com.web2team.graphql.resolver.GridLayout;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Grid.GridLayout;
import com.web2team.graphql.model.Grid.GridLayoutItem;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutQuery implements GraphQLQueryResolver {
  private GridLayoutRepository gridLayoutRepository;
  private GridLayoutItemRepository gridLayoutItemRepository;

  public Iterable<GridLayout> getGridLayouts(Long userId) {
    return gridLayoutRepository.getAllByUserId(userId);
  }

  public Iterable<GridLayoutItem> getGridLayoutItems(Long gridLayoutId) {
    return gridLayoutItemRepository.getAllByGridLayoutId(gridLayoutId);
  }
}
