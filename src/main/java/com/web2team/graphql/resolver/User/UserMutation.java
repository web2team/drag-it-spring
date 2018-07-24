package com.web2team.graphql.resolver.User;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutation implements GraphQLMutationResolver {
  private UserRepository userRepository;

  public User newUser(String user_id, String password, String name, String email) {
    System.out.println(name);

    User user = new User();
    user.setNickname(name);
    user.setEmail(email);
    user.setUsername(user_id);
    user.setPassword(password);

    userRepository.save(user);

    return user;
  }
}
