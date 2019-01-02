package com.org.streamkafka.service;

import com.org.streamkafka.model.Greetings;
import com.org.streamkafka.stream.GreetingsStreams;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreetingsListener {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafkaTopic}")
    private String kafkaTopicName;
	
    @StreamListener(GreetingsStreams.INPUT)
    public void handleGreetings(@Payload Greetings greetings) {
        //log.info("Received greetings: {}", greetings);
        System.out.println("Received greetings in Listener {}"+ greetings);
        
        try {
        	
        	System.out.println("posting greeting to topic "+kafkaTopicName);
        	
        	kafkaTemplate.send(kafkaTopicName, greetings.toString());
        	
        }
        catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
    }
}
