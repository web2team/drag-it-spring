package com.web2team.graphql.resolver.User;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.exception.UserNotFoundException;
import com.web2team.graphql.model.User.User;
import com.web2team.graphql.repository.User.UserRepository;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
  private UserRepository userRepository;

  public Iterable<User> findAllUsers(DataFetchingEnvironment env) {
    return userRepository.findAll();
  }

  public User findOneUserByUserName(String username) {
    for (User user : userRepository.findAll()) {
      if (user.getName().equals(username)) {
        return user;
      }
    }

    throw new UserNotFoundException("User Not Found Error By Name", username);
  }

  public User findOneUserByUserId(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User Not Found Error By Id", id));
  }

  public long countUsers() {
    return userRepository.count();
  }
}
