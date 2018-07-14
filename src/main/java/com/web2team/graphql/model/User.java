package com.web2team.graphql.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_time", nullable = false)
  private Date created_time;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_time", nullable = false)
  private Date updated_time;

  public User() {}

  public User(Long id) {
    this.id = id;
  }

  public User(String username, String password, String nickname, String email) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @PrePersist
  protected void onCreate() {
    this.updated_time = this.created_time = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_time = new Date();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return username.equals(user.username);
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }

  @Override
  public String toString() {
    return "User{"
        + "username="
        + username
        + ", nickname='"
        + nickname
        + '\''
        + ", email='"
        + email
        + '\''
        + '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
