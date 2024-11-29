package org.lididimi.nltworkshop.service;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;

public interface BaseService {
    boolean isImported();

    void seedData() throws JAXBException, IOException, JAXBException;

    String readFile() throws IOException;
}
