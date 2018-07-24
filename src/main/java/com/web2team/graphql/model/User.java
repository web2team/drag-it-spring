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
  @Column(name = "created_at", nullable = false)
  private Date created_at;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updated_at;

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
    this.updated_at = this.created_at = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_at = new Date();
  }
}
