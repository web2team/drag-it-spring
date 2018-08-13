package com.web2team.graphql.repository.Chat;

import com.web2team.graphql.model.Chat.ChatThread;
import org.springframework.data.repository.CrudRepository;

public interface ChatThreadRepository extends CrudRepository<ChatThread, Long> {}
