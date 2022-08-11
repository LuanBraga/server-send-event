package com.sse.serversendevent.message.controller;

import com.sse.serversendevent.message.model.Message;
import com.sse.serversendevent.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    private SseEmitter emitter;
//    private SseEmitter.SseEventBuilder event;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/sse-emitter")
    public SseEmitter sseEmitter() {
//        SseEmitter emitter = new SseEmitter(150_00000L);
        SseEmitter emitter = new SseEmitter(180_0000L);

        this.emitter = emitter;

        return emitter;
    }

    @PostMapping
    public void registerNewMessage(@RequestBody Message message) {
        messageService.addNewMessage(message, emitter);
    }
}
