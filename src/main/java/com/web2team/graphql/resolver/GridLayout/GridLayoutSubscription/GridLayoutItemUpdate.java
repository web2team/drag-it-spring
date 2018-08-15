package com.web2team.graphql.resolver.GridLayout.GridLayoutSubscription;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import com.web2team.graphql.model.Grid.GridLayoutItem;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GridLayoutItemUpdate implements GraphQLSubscriptionResolver {
  private GridLayoutItemPublisher gridLayoutItemPublisher;

  Publisher<GridLayoutItem> linkGridLayoutItem(Long gridLayoutId) {
      return gridLayoutItemPublisher.getPublisher(gridLayoutId);
  }
}
