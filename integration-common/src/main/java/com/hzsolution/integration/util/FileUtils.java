package com.hzsolution.integration.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileUtils {
    public static void dropTestSourceFiles() throws IOException, InterruptedException {
        createFile("person1.json", "{\"person\": { \"name\":\"Charles Lee\",\"email\": \"cl@gmail.com\"}}");
        createFile("person2.json", "{\"person\": { \"name\":\"Wilson Graze\",\"email\": \"wg@gmail.com\"}}");
        createFile("person3.json", "{\"person\": { \"name\":\"Addison Pro\",\"email\": \"ap@gmail.com\"}}");
    }

    private static void createFile(String fileName, String content) throws IOException, InterruptedException {
        writeFile(IntegrationConst.INPUT_DIR, fileName, content, false);
    }

    private static void appendFile(String fileName, String content) throws IOException, InterruptedException {
        writeFile(IntegrationConst.INPUT_DIR, fileName, content, true);
    }

    public static void writeFile(String dir, String fileName, String content, boolean append)
            throws IOException, InterruptedException {
        File newFile = new File(dir + fileName);
        org.apache.commons.io.FileUtils.writeStringToFile(newFile, content, Charset.forName("UTF-8"), append);
        Thread.sleep(1000);
    }

    public static void removeInputFile(String fileName) {
        org.apache.commons.io.FileUtils.deleteQuietly(new File(fileName));
    }
}
