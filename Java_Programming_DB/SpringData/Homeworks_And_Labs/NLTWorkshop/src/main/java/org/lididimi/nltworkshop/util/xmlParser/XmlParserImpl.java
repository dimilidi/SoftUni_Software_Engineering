package org.lididimi.nltworkshop.util.xmlParser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XmlParserImpl implements XmlParser {

    public <T> T fromFile(File file, Class<T> object) throws JAXBException, FileNotFoundException {

        final JAXBContext context = JAXBContext.newInstance(object);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final FileReader reader = new FileReader(file);

        return (T) unmarshaller.unmarshal(reader);
    }
}