package com.sse.serversendevent.message.controller;

import com.sse.serversendevent.message.service.ServerSendEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("api/v1/sse")
public class ServerSendEventController {
    private final ServerSendEventService serverSendEventService;

    @Autowired
    public ServerSendEventController(ServerSendEventService serverSendEventService) {
        this.serverSendEventService = serverSendEventService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/sse-emitter")
    public SseEmitter sseEmitter() {
        SseEmitter emitter = new SseEmitter(180_0000L);

        this.serverSendEventService.setEmitter(emitter);

        return emitter;
    }
}
