package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static  void writeXMLIntoFile(Object dto, Path filePath) throws JAXBException, IOException {
        createFileIfNotExist(filePath);

        final JAXBContext context = JAXBContext.newInstance(dto.getClass());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dto, filePath.toFile());
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