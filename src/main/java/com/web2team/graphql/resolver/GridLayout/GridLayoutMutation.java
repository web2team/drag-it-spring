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
import com.web2team.graphql.resolver.Notification.NotificationHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class GridLayoutMutation implements GraphQLMutationResolver {
  private GridLayoutRepository GLRepo;
  private GridLayoutItemRepository GLItemRepo;
  private GridLayoutItemPropsRepository GLItemPropsRepo;
  private GridLayoutItemPositionRepository GLItemPositionRepo;

  private UserRepository userRepo;

  private GridLayoutItemPropsUtility GLItemPropsUtil;
  private RxBus<GridLayoutItem> GLItemRxBus;

  private NotificationHelper notificationHelper;

  public GridLayoutItemPosition updateGridLayout(
      Long gridLayoutId, Long gridLayoutItemId, GridLayoutItemPosition newGridLayoutItemPosition)
      throws InstantiationException, IllegalAccessException {

    GridLayoutItemPosition origin =
        GLItemRepo.findByGridLayoutIdEqualsAndIdEquals(gridLayoutId, gridLayoutItemId)
            .orElseThrow(
                () -> new NoSuchElementException("invalid gridLayoutId and gridLayoutItemId"))
            .getGridLayoutItemPosition();

    GridLayoutItemPosition toSave = mergeObjects(origin, newGridLayoutItemPosition);

    return GLItemPositionRepo.save(toSave);
  }

  public GridLayout updateGridLayoutName(Long gridLayoutId, String name) {
    GridLayout target =
        GLRepo.findById(gridLayoutId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId"));

    target.setName(name);

    return GLRepo.save(target);
  }

  public GridLayout newGridLayout(String name, Long userId) {
    GridLayout gridLayout = new GridLayout();

    User user =
        userRepo.findById(userId).orElseThrow(() -> new NoSuchElementException("invalid userId"));

    gridLayout.setName(name);
    gridLayout.setUser(user);

    return GLRepo.save(gridLayout);
  }

  public GridLayoutItem newGridLayoutItem(Long gridLayoutId, GridLayoutItemPropsInput propsInput) {
    GridLayoutItem newGLItem = new GridLayoutItem();

    GridLayout toSetGridLayout = GLRepo.findById(gridLayoutId).get();
    newGLItem.setGridLayout(toSetGridLayout);

    GridLayoutItemProps props =
        GLItemPropsRepo.save(GLItemPropsUtil.generateGridLayoutItemProps(propsInput));
    newGLItem.setGridLayoutItemProps(props);

    GridLayoutItemPosition newPosition = GLItemPositionRepo.save(new GridLayoutItemPosition());
    newGLItem.setGridLayoutItemPosition(newPosition);

    GridLayoutItem savedGridLayoutItem = GLItemRepo.save(newGLItem);
    GLItemRxBus.send(savedGridLayoutItem);

    List<User> to = propsInput.getChatThreadInput().getUsers();
    User from = toSetGridLayout.getUser();
    notificationHelper.publishNotification(to, from, GridLayoutItemType.CHATTING, props);

    return savedGridLayoutItem;
  }

  public Boolean deleteGridLayout(Long gridLayoutId) {

    GridLayout gridLayout =
        GLRepo.findById(gridLayoutId)
            .orElseThrow(() -> new NoSuchElementException("invalid gridLayoutId"));
    GLRepo.delete(gridLayout);

    return true;
  }

  public Boolean deleteGridLayoutItem(Long gridLayoutItemId) {
    GLItemRepo.deleteById(gridLayoutItemId);

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
