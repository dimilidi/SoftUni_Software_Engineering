package org.lididimi.jsonparsing._01ProductShop.servicies;

import java.io.IOException;

public interface UserService {

    String findAllUsersWithSoldProductsToAtLeastOneBuyer() throws IOException;

    String findUsersWithSoldProductsAndCount() throws IOException;
}
