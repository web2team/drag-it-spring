package com.web2team.graphql.resolver.User;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.repository.Chat.ChatThreadRepository;
import com.web2team.graphql.repository.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutation implements GraphQLMutationResolver {
  private UserRepository userRepository;
  private ChatThreadRepository chatThreadRepository;
}
