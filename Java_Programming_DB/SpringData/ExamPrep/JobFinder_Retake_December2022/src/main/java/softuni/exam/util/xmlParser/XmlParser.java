package softuni.exam.util.xmlParser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> T fromFile(File file, Class<T> object) throws JAXBException, FileNotFoundException;
}