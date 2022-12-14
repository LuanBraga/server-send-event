package com.sse.serversendevent.message.service;

import com.sse.serversendevent.message.model.Message;
import com.sse.serversendevent.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    private final ServerSendEventService serverSendEventService;

    @Autowired
    public MessageService(MessageRepository messageRepository, ServerSendEventService serverSendEventService) {
        this.messageRepository = messageRepository;
        this.serverSendEventService = serverSendEventService;
    }

    public List<Message> getMessages() {
        return  messageRepository.findAll();
    }

    public void addNewMessage(Message message) {
        Optional<Message> messageOptional = messageRepository.findById(message.getId());

        if(messageOptional.isPresent()) {
            throw new IllegalStateException("this message already exists");
        }
        messageRepository.save(message);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .id(String.valueOf(message.getId()))
                    .name("message")
                    .data(message);
                this.serverSendEventService.getEmitter().send(event);
            } catch (Exception ex) {
                this.serverSendEventService.getEmitter().completeWithError(ex);
            }
        });
    }

    public void deleteMessage(Long id) {
        Optional<Message> messageOptional = messageRepository.findById(id);

        if(messageOptional.isPresent()) {
            messageRepository.deleteById(id);

            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .id(String.valueOf(id))
                            .name("delete message")
                            .data("message with id " + id + " has been deleted");
                    this.serverSendEventService.getEmitter().send(event);
                } catch (Exception ex) {
                    this.serverSendEventService.getEmitter().completeWithError(ex);
                }
            });
        } else {
            throw new IllegalStateException("this message not exists");
        }
    }
}
