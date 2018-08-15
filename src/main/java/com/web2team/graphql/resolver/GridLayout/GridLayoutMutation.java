package com.web2team.graphql.resolver.GridLayout;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.Grid.*;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import com.web2team.graphql.repository.GridLayout.utility.GridLayoutItemPropsUtility;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class GridLayoutMutation implements GraphQLMutationResolver {
  private GridLayoutRepository gridLayoutRepository;
  private GridLayoutItemRepository gridLayoutItemRepository;
  private GridLayoutItemPropsRepository gridLayoutItemPropsRepository;
  private GridLayoutItemPositionRepository gridLayoutItemPositionRepository;

  private UserRepository userRepository;

  private GridLayoutItemPropsUtility gridLayoutItemPropsUtility;
  private RxBus<GridLayoutItem> gridLayoutItemRxBus;

  public GridLayoutItemPosition updateGridLayout(
      Long gridLayoutId, Long gridLayoutItemId, GridLayoutItemPosition newGridLayoutItemPosition)
      throws InstantiationException, IllegalAccessException {

    GridLayoutItemPosition origin =
        gridLayoutItemRepository
            .findByGridLayoutIdEqualsAndIdEquals(gridLayoutId, gridLayoutItemId)
            .orElseThrow(
                () -> new NoSuchElementException("invalid gridLayoutId and gridLayoutItemId"))
            .getGridLayoutItemPosition();

    GridLayoutItemPosition toSave = mergeObjects(origin, newGridLayoutItemPosition);

    return gridLayoutItemPositionRepository.save(toSave);
  }

  public GridLayout updateGridLayoutName(Long gridLayoutId, String name) {
    GridLayout target =
        gridLayoutRepository
            .findById(gridLayoutId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId"));

    target.setName(name);

    return gridLayoutRepository.save(target);
  }

  public GridLayout newGridLayout(String name, Long userId) {
    GridLayout gridLayout = new GridLayout();

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("invalid userId"));

    gridLayout.setName(name);
    gridLayout.setUser(user);

    return gridLayoutRepository.save(gridLayout);
  }

  public GridLayoutItem newGridLayoutItem(
      Long gridLayoutId,
      GridLayoutItemType gridLayoutItemType,
      GridLayoutItemPropsInput gridLayoutItemPropsInput) {

    GridLayoutItem gridLayoutItem = new GridLayoutItem();

    gridLayoutItem.setGridLayout(
        gridLayoutRepository
            .findById(gridLayoutId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId")));

    gridLayoutItem.setGridLayoutItemType(gridLayoutItemType);

    gridLayoutItem.setGridLayoutItemProps(
        gridLayoutItemPropsRepository.save(
            gridLayoutItemPropsUtility.generateGridLayoutItemProps(
                gridLayoutItemType, gridLayoutItemPropsInput)));

    gridLayoutItem.setGridLayoutItemPosition(
        gridLayoutItemPositionRepository.save(new GridLayoutItemPosition()));

    GridLayoutItem savedGridLayoutItem = gridLayoutItemRepository.save(gridLayoutItem);

    gridLayoutItemRxBus.send(savedGridLayoutItem);
    return savedGridLayoutItem;
  }

  public Boolean deleteGridLayout(Long gridLayoutId) {

    GridLayout gridLayout =
        gridLayoutRepository
            .findById(gridLayoutId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId"));
    gridLayoutRepository.delete(gridLayout);

    return true;
  }

  public Boolean deleteGridLayoutItem(Long gridLayoutItemId) {
    gridLayoutItemRepository.deleteById(gridLayoutItemId);

    return true;
  }

  @SuppressWarnings("unchecked")
  public static <T> T mergeObjects(T first, T second)
      throws IllegalAccessException, InstantiationException {
    Class<?> clazz = first.getClass();
    Field[] fields = clazz.getDeclaredFields();
    Object returnValue = clazz.newInstance();

    for (Field field : fields) {
      field.setAccessible(true);

      Object value1 = field.get(first);
      Object value2 = field.get(second);

      if (value2 != null) {
        field.set(returnValue, value2);
        continue;
      }
      field.set(returnValue, value1);
    }
    return (T) returnValue;
  }
}
