package org.lididimi.jsonparsing._01ProductShop.utils;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Utils {

    public static void writeJsonIntoFile(List<?> objects, Path filePath) throws IOException {

        createFileIfNotExist(filePath);

        final FileWriter fileWriter = new FileWriter(filePath.toFile());
        final Gson gson = new Gson();

        gson.toJson(objects, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    public static void writeJsonIntoFile(Object object, Path filePath) throws IOException {

        createFileIfNotExist(filePath);

        final FileWriter fileWriter = new FileWriter(filePath.toFile());
        final Gson gson = new Gson();

        gson.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    private static void createFileIfNotExist(Path filePath) throws IOException {
        // Ensure the parent directories exist
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Create the file if it doesn't exist
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
}