package com.web2team.graphql.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  private String name;

  private String phone;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @PrePersist
  protected void onCreate() {
    this.updatedAt = this.createdAt = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = new Date();
  }
}
