package com.web2team.graphql.resolver.Project;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Project;
import com.web2team.graphql.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class ProjectQuery implements GraphQLQueryResolver {
  private ProjectRepository projectRepository;

  public Project getProject(Long projectId) {
    return projectRepository
        .findById(projectId)
        .orElseThrow(() -> new NoSuchElementException("invalid projectId"));
  }
}
