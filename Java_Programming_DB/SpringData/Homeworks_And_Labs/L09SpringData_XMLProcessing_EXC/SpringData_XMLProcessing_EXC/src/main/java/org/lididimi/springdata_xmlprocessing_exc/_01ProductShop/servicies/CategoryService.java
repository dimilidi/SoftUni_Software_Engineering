package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface CategoryService {

    String getCategoriesByProductSummary() throws IOException, JAXBException;
}
