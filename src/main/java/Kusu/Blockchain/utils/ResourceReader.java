package Kusu.Blockchain.utils;

import java.io.*;
import java.util.List;


public class ResourceReader {

    public static List<String> readResourceFile(String fileName) throws IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }



        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream))) {
            return reader.lines().toList();
        }

    }


}
