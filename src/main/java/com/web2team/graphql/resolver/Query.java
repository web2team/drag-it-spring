package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.exception.BookNotFoundException;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
  private BookRepository bookRepository;

  @Autowired
  public Query(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> findAllBooks() {

    return bookRepository.findAll();
  }

  public long countBooks() {
    return bookRepository.count();
  }

  public Book findOneBook(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(() -> new BookNotFoundException("not found", id));
  }
}
