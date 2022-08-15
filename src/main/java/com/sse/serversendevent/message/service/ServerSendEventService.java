package com.sse.serversendevent.message.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Data
public class ServerSendEventService {
    private SseEmitter emitter;
}
