package com.web2team.graphql.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "phone")
  private String phone;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false)
  private Date created_at;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Date updated_at;

  public User(Long id) {
    this.id = id;
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
