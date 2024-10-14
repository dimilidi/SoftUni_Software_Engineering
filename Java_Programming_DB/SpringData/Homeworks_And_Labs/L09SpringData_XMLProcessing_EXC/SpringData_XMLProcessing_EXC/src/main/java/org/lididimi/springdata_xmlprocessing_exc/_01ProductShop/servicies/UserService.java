package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;

public interface UserService {

    String findAllUsersWithSoldProductsToAtLeastOneBuyer() throws IOException, JAXBException;

    String findUsersWithSoldProductsAndCount() throws IOException, JAXBException;
}
