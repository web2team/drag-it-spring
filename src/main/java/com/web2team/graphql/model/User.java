package com.web2team.graphql.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
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

  public User(Long id) {
    this.id = id;
  }

  public User(String username, String password, String nickname, String email) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
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
}
