package com.web2team.graphql.resolver.Grid;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.Grid.Grid;
import com.web2team.graphql.model.Grid.GridData;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.Grid.GridDataRepository;
import com.web2team.graphql.repository.Grid.GridDraggableLayoutRepository;
import com.web2team.graphql.repository.Grid.GridRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class GridMutation implements GraphQLMutationResolver {
  private GridDraggableLayoutRepository gridDraggableLayoutRepository;
  private GridDataRepository gridDataRepository;
  private GridRepository gridRepository;
  private UserRepository userRepository;

  public GridData updateGridLayout(Long gridId, Long gridDraggablePropsId, GridData newGridData)
      throws InstantiationException, IllegalAccessException {

    GridData origin =
        gridDraggableLayoutRepository
            .findByGridIdEqualsAndIdEquals(gridId, gridDraggablePropsId)
            .getGridData();

    GridData toSave = mergeObjects(origin, newGridData);

    return gridDataRepository.save(toSave);
  }

  public Grid updateGridName(Long gridId, String name) {
    Grid target =
        gridRepository
            .findById(gridId)
            .orElseThrow(() -> new NoSuchElementException("invalid grid id"));

    target.setName(name);

    return gridRepository.save(target);
  }

  public Grid newGrid(String name, Long userId) {
    Grid grid = new Grid();

    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NoSuchElementException("invalid user id"));

    grid.setName(name);
    grid.setUser(user);

    return gridRepository.save(grid);
  }

  public Boolean deleteGrid(Long gridId) {

    Grid grid =
        gridRepository
            .findById(gridId)
            .orElseThrow(() -> new NoSuchElementException("invalid grid id"));
    gridRepository.delete(grid);

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
