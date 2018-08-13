package com.web2team.graphql.model.Chat;

import com.web2team.graphql.model.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ChatThread {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "map_user_chat_thread",
      joinColumns = @JoinColumn(name = "chat_thread_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> users;
}
