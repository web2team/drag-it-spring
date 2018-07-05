package com.web2team.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.web2team.graphql.exception.BookNotFoundException;
import com.web2team.graphql.model.Author;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.repository.AuthorRepository;
import com.web2team.graphql.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {
  private BookRepository bookRepository;
  private AuthorRepository authorRepository;

  public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
  }

  public Author newAuthor(String firstName, String lastName) {
    Author author = new Author();
    author.setFirstName(firstName);
    author.setLastName(lastName);

    authorRepository.save(author);

    return author;
  }

  public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
    Book book = new Book();
    book.setAuthor(new Author(authorId));
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
