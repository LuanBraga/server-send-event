package com.sse.serversendevent.message.controller;

import com.sse.serversendevent.message.model.Message;
import com.sse.serversendevent.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @PostMapping
    public void registerNewMessage(@RequestBody Message message) {
        messageService.addNewMessage(message);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
