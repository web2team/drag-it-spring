package com.web2team.graphql.resolver.GridLayout;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Grid.GridLayout;
import com.web2team.graphql.model.Grid.GridLayoutItem;
import com.web2team.graphql.model.Grid.GridLayoutItemPosition;
import com.web2team.graphql.model.Grid.GridLayoutItemProps;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import com.web2team.graphql.repository.GridLayout.utility.GridLayoutItemPropsUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutItemHelper {
  private GridLayoutRepository GLRepo;
  private GridLayoutItemRepository GLItemRepo;
  private GridLayoutItemPropsRepository GLItemPropsRepo;
  private GridLayoutItemPositionRepository GLItemPositionRepo;
  private GridLayoutItemPropsUtility GLItemPropsUtil;
  private RxBus<GridLayoutItem> GLItemRxBus;

  public GridLayoutItem generateNewGridLayoutItem(Long gridLayoutId, GridLayoutItemProps props) {
    GridLayoutItem newGLItem = new GridLayoutItem();

    GridLayout toSetGridLayout = GLRepo.findById(gridLayoutId).get();
    newGLItem.setGridLayout(toSetGridLayout);

    newGLItem.setGridLayoutItemProps(props);

    GridLayoutItemPosition newPosition = GLItemPositionRepo.save(new GridLayoutItemPosition());
    newGLItem.setGridLayoutItemPosition(newPosition);

    GridLayoutItem saved = GLItemRepo.save(newGLItem);
    GLItemRxBus.send(saved);

    return saved;
  }
}
