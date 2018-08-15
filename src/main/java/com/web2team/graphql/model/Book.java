package com.web2team.graphql.model;

import com.web2team.graphql.model.User.User;

import javax.persistence.*;

@Entity
public class Book {
  @Id
  @Column(name = "book_id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "book_title", nullable = false)
  private String title;

  @Column(name = "book_isbn", nullable = false)
  private String isbn;

  @Column(name = "book_pageCount", nullable = false)
  private int pageCount;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  public Book() {}

  public Book(String title, String isbn, int pageCount, User user) {
    this.title = title;
    this.isbn = isbn;
    this.pageCount = pageCount;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    return id.equals(book.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "Book{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", isbn='"
        + isbn
        + '\''
        + ", pageCount="
        + pageCount
        + ", user="
        + user
        + '}';
  }
}
