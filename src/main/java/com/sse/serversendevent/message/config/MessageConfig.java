package com.sse.serversendevent.message.config;

import com.sse.serversendevent.message.model.Message;
import com.sse.serversendevent.message.repository.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MessageConfig {

    @Bean
    CommandLineRunner commandLineRunner(MessageRepository repository){
        return args -> {
            Message message1 =  new Message(
                    "Message 1, Testing",
                    "http://localhost"
            );

            Message message2 = new Message(
                    "Message 2, Testing",
                    "http://localhost"
            );

            Message message3 = new Message(
                    "Message 3, Testing",
                    "http://localhost"
            );

            repository.saveAll(
                    Arrays.asList(message1, message2, message3)
            );
        };
    }
}
