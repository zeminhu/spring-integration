package com.hzsolution.integration.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class QueueProcessApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QueueProcessApplication.class, args);
    }
}

