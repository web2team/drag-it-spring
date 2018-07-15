package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.UserRepository;
import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLName;
import org.springframework.beans.factory.annotation.Autowired;
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
