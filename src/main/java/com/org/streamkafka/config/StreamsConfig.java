package com.org.streamkafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.org.streamkafka.stream.GreetingsStreams;

@EnableBinding(GreetingsStreams.class)
public class StreamsConfig {
}
