package com.web2team.graphql.resolver.Project;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.MapProjectGridLayout.MapProjectGridLayout;
import com.web2team.graphql.model.Project;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.MapProjectGridLayoutRepository;
import com.web2team.graphql.repository.ProjectRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class ProjectMutation implements GraphQLMutationResolver {
  private MapProjectGridLayoutRepository mapProjectGridLayoutRepository;
  private ProjectRepository projectRepository;
  private UserRepository userRepository;

  public Project addGridLayoutToProject(Long gridLayoutId, Long projectId) {
    MapProjectGridLayout item = new MapProjectGridLayout();
    item.setGridLayoutId(gridLayoutId);
    item.setProjectId(projectId);

    mapProjectGridLayoutRepository.save(item);

    return projectRepository
        .findById(projectId)
        .orElseThrow(() -> new RuntimeException("not found project"));
  }

  public Project newProject(Long userId) {
    Project project = new Project();
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("invalid userId"));
    project.setUsers(Arrays.asList(user));

    return projectRepository.save(project);
  }
}
