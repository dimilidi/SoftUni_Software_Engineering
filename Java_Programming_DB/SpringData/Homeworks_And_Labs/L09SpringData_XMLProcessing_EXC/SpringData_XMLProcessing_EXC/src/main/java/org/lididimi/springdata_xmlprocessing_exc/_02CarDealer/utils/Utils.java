package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.NO_ENTITIES_FOUND_IN_REPOSITORY;

public class Utils {

    private final Random random;

    public Utils(Random random) {
        this.random = random;
    }

    public static  void writeXMLIntoFile(Object dto, Path filePath) throws JAXBException, IOException {
        createFileIfNotExist(filePath);

        final JAXBContext context = JAXBContext.newInstance(dto.getClass());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dto, filePath.toFile());
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