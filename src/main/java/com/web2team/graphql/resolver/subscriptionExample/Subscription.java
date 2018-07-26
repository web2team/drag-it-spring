package com.web2team.graphql.resolver.subscriptionExample;

import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class Subscription implements GraphQLSubscriptionResolver {

  private StockTickerPublisher stockTickerPublisher;

  Subscription(StockTickerPublisher stockTickerPublisher) {
    this.stockTickerPublisher = stockTickerPublisher;
  }

  Publisher<StockPriceUpdate> stockQuotes(List<String> stockCodes) {
    return stockTickerPublisher.getPublisher(stockCodes);
  }
}
