package com.hzsolution.integration;

import com.hzsolution.integration.file.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FileProcessApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(FileProcessApplication.class, args);

        FileUtils.dropTestSourceFiles();
    }
}

