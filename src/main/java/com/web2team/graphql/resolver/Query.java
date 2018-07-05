package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.web2team.graphql.model.Author;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.repository.AuthorRepository;
import com.web2team.graphql.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public Iterable<Book> findAllBooks() {
    return bookRepository.findAll();
  }

  public Iterable<Author> findAllAuthors() {
    return authorRepository.findAll();
  }

  public long countBooks() {
    return bookRepository.count();
  }

  public long countAuthors() {
    return authorRepository.count();
  }
}
