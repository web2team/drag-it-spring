package com.web2team.graphql.model.Chat;

import com.web2team.graphql.model.User.User;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ChatThread {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "map_user_chat_thread",
      joinColumns = @JoinColumn(name = "chat_thread_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<User> users;
}
