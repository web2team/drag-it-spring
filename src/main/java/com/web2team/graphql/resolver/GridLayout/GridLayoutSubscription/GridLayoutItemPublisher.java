package com.web2team.graphql.resolver.GridLayout.GridLayoutSubscription;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Grid.GridLayoutItem;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutItemPublisher {
  private final RxBus<GridLayoutItem> gridLayoutItemRxBus;

  Flowable<GridLayoutItem> getPublisher(Long gridLayoutId) {
    return gridLayoutItemRxBus
        .toObservable()
        .filter(gridLayoutItem -> gridLayoutItem.getGridLayout().getId().equals(gridLayoutId))
        .toFlowable(BackpressureStrategy.BUFFER);
  }
}
