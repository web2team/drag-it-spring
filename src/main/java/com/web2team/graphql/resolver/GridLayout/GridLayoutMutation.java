package com.web2team.graphql.resolver.GridLayout;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.Chat.ChatThread;
import com.web2team.graphql.model.Grid.*;
import com.web2team.graphql.model.MapUserChatThread.MapUserChatThread;
import com.web2team.graphql.model.Project;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPositionRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemPropsRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutItemRepository;
import com.web2team.graphql.repository.GridLayout.GridLayoutRepository;
import com.web2team.graphql.repository.GridLayout.utility.GridLayoutItemPropsUtility;
import com.web2team.graphql.repository.MapUserChatThread.MapUserChatThreadRepository;
import com.web2team.graphql.repository.User.UserRepository;
import com.web2team.graphql.resolver.Notification.NotificationHelper;
import com.web2team.graphql.resolver.Project.ProjectMutation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GridLayoutMutation implements GraphQLMutationResolver {
  private GridLayoutRepository GLRepo;
  private GridLayoutItemRepository GLItemRepo;
  private GridLayoutItemPropsRepository GLItemPropsRepo;
  private GridLayoutItemPositionRepository GLItemPositionRepo;
  private UserRepository userRepo;
  private GridLayoutItemPropsUtility GLItemPropsUtil;
  private NotificationHelper notificationHelper;
  private GridLayoutItemHelper GLItemHelper;
  private MapUserChatThreadRepository mapUserChatThreadRepository;
  private ProjectMutation projectMutation;

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

    GridLayout saved = GLRepo.save(gridLayout);
    Project project = projectMutation.newProject(userId);

    projectMutation.addGridLayoutToProject(saved.getId(), project.getId());

    return gridLayout;
  }

  public GridLayoutItem newGridLayoutItemAndNotify(
      Long gridLayoutId, GridLayoutItemPropsInput propsInput) {
    GridLayoutItemProps props =
        GLItemPropsRepo.save(GLItemPropsUtil.generateGridLayoutItemProps(propsInput));
    GridLayoutItem newItem = GLItemHelper.generateNewGridLayoutItem(gridLayoutId, props);

    User from = newItem.getGridLayout().getUser();
    List<User> to =
        propsInput
            .getChatThreadInput()
            .getUsers()
            .stream()
            .filter((user) -> !user.getId().equals(from.getId()))
            .collect(Collectors.toList());
    GridLayoutItemType type = newItem.getGridLayoutItemProps().getType();
    notificationHelper.publishNotification(to, from, type, props);

    ChatThread chatThread = props.getChatThread();
    String threadName = propsInput.getChatThreadInput().getThreadName();
    MapUserChatThread map =
        mapUserChatThreadRepository
            .findByUserIdEqualsAndChatThreadIdEquals(from.getId(), chatThread.getId())
            .get();
    map.setName(threadName);
    mapUserChatThreadRepository.save(map);

    return newItem;
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
