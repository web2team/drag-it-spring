package com.web2team.graphql.resolver.Grid;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.Grid.GridData;
import com.web2team.graphql.repository.Grid.GridDataRepository;
import com.web2team.graphql.repository.Grid.GridDraggableLayoutRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@AllArgsConstructor
public class GridMutation implements GraphQLMutationResolver {
  private GridDraggableLayoutRepository gridRepository;
  private GridDataRepository gridDataRepository;

  public GridData changeGridLayout(Long grid_id, Long gridDraggableProps_id, GridData newGridData)
      throws InstantiationException, IllegalAccessException {

    GridData origin =
        gridRepository.findByGridIdEqualsAndIdEquals(grid_id, gridDraggableProps_id).getGridData();

    GridData toSave = mergeObjects(origin, newGridData);

    return gridDataRepository.save(toSave);
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
