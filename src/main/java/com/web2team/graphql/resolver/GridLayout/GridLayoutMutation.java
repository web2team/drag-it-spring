package com.web2team.graphql.resolver.GridLayout;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.Grid.GridLayout;
import com.web2team.graphql.model.Grid.GridLayoutItemPosition;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
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
  private GridLayoutItemPositionRepository gridLayoutItemPositionRepository;

  private UserRepository userRepository;

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

  public GridLayout updateGridLayoutName(Long gridId, String name) {
    GridLayout target =
        gridLayoutRepository
            .findById(gridId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridId"));

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

  public Boolean deleteGridLayout(Long gridId) {

    GridLayout gridLayout =
        gridLayoutRepository
            .findById(gridId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridId"));
    gridLayoutRepository.delete(gridLayout);

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
