package org.lididimi.jsonparsing._02CarDealer.utils;

import com.google.gson.Gson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.NO_ENTITIES_FOUND_IN_REPOSITORY;

public class Utils {

    private final Random random;

    public Utils(Random random) {
        this.random = random;
    }

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


    public static <T> long getRandomEntityId(JpaRepository<T, Long> repository, Random random) {
        long count = repository.count();
        if (count == 0) {
            throw new IllegalStateException(NO_ENTITIES_FOUND_IN_REPOSITORY);
        }
        return random.nextLong(count) + 1L;
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