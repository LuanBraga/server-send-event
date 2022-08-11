package com.sse.serversendevent.message.repository;

import com.sse.serversendevent.message.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
