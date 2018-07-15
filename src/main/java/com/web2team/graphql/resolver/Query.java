package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.exception.BookNotFoundException;
import com.web2team.graphql.exception.UserNotFoundException;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.BookRepository;
import com.web2team.graphql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
  private BookRepository bookRepository;
  private UserRepository userRepository;

  @Autowired
  public Query(UserRepository userRepository, BookRepository bookRepository) {
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Iterable<User> findAllUsers() {
    return userRepository.findAll();
  }

  public User findOneUserByUserName(String username) {
    for (User user : userRepository.findAll()) {
      if (user.getUsername().equals(username)) {
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

  public long countBooks() {
    return bookRepository.count();
  }

  public long countUsers() {
    return userRepository.count();
  }

  public Book findOneBook(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookNotFoundException("not found", id));
  }
}
