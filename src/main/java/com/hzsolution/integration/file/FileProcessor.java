package com.hzsolution.integration.file;

import com.hzsolution.integration.config.IntegrationConfig;
import org.springframework.messaging.Message;

public class FileProcessor {
    private static final String HEADER_FILE_NAME = "file_name";
    private static final String MSG = "%s received. Content: %s";
    private static final String CLEANUP_MSG = "%s removed.";

    public void process(Message<String> msg) throws Exception {
        System.out.println("process messages start ...");

        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        String content = msg.getPayload();

        System.out.println(String.format(MSG, fileName, content));

        FileUtils.writeFile(IntegrationConfig.OUTPUT_DIR, fileName, content, false);

        String path = IntegrationConfig.INPUT_DIR + fileName;
        FileUtils.removeInputFile(path);
        System.out.println(String.format(CLEANUP_MSG, path));
    }

    public void cleanupDropzone(Message<String> msg) {
        System.out.println("cleanup dropzone ");

        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        FileUtils.removeInputFile(fileName);
    }

}

