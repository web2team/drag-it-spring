package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.web2team.graphql.exception.AuthorNotFoundException;
import com.web2team.graphql.model.Author;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
  private AuthorRepository authorRepository;

  public BookResolver(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  public Author getAuthor(Book book) {
    return authorRepository
        .findById(book.getAuthor().getId())
        .orElseThrow(() -> new AuthorNotFoundException("Author id not found", book.getAuthor().getId()));
  }
}
