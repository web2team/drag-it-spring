package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.web2team.graphql.exception.UserNotFoundException;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserResolver implements GraphQLResolver<Book> {
  private UserRepository userRepository;

  public UserResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User getUser1234(Book book) {
    return userRepository
        .findById(book.getUser().getId())
        .orElseThrow(
            () -> new UserNotFoundException("User id not found", book.getUser().getUsername()));
  }
}
