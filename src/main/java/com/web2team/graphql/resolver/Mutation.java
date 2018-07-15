package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.exception.BookNotFoundException;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.BookRepository;
import com.web2team.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
  private BookRepository bookRepository;
  private UserRepository userRepository;

  public Mutation(UserRepository userRepository, BookRepository bookRepository) {
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  public User newUser(String user_id, String password, String name, String email) {
    User user = new User();
    user.setNickname(name);
    user.setEmail(email);
    user.setUsername(user_id);
    user.setPassword(password);

    userRepository.save(user);

    return user;
  }

  public Book newBook(String title, String isbn, Integer pageCount, Long userId) {
    Book book = new Book();

    book.setUser(new User(userId));
    book.setTitle(title);
    book.setIsbn(isbn);
    book.setPageCount(pageCount != null ? pageCount : 0);

    bookRepository.save(book);

    return book;
  }

  public boolean deleteBook(Long id) {
    bookRepository.deleteById(id);
    return true;
  }

  public Book updateBookPageCount(Integer pageCount, Long id) {
    Book book =
        bookRepository
            .findById(id)
            .orElseThrow(() -> new BookNotFoundException("The book to be updated was found", id));

    book.setPageCount(pageCount);

    bookRepository.save(book);

    return book;
  }
}
