package com.web2team.graphql.resolver.User;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

  private final UserRepository userRepository;

  @Autowired
  public UserMutation(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User newUser(String user_id, String password, String name, String email) {
    System.out.println(name);

    User user = new User();
    user.setEmail(email);
    user.setUsername(user_id);
    user.setPassword(password);

    userRepository.save(user);

    return user;
  }
}
