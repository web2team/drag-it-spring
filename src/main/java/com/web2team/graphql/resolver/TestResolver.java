package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.User.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class TestResolver implements GraphQLQueryResolver {
  private UserRepository userRepository;

  public TestResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Iterable<User> findAllUserOnUserResolver() {
    return userRepository.findAll();
  }
}
