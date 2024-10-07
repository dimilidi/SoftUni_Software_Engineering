package org.lididimi.jsonparsing._01ProductShop.servicies.impl;

import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.UserDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.UserWithProductsDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.UserWithSoldProductDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.UsersWithProductsWrapperDTO;
import org.lididimi.jsonparsing._01ProductShop.repositories.UserRepository;
import org.lididimi.jsonparsing._01ProductShop.servicies.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.lididimi.jsonparsing._01ProductShop.constants.Constants.*;
import static org.lididimi.jsonparsing._01ProductShop.constants.Paths.USERS_AND_PRODUCTS_FILE_PATH;
import static org.lididimi.jsonparsing._01ProductShop.constants.Paths.USER_WITH_SOLD_PRODUCTS_FILE_PATH;
import static org.lididimi.jsonparsing._01ProductShop.utils.Utils.writeJsonIntoFile;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }


    @Override
    public String findAllUsersWithSoldProductsToAtLeastOneBuyer() throws IOException {

        final List<UserWithSoldProductDTO> userWithSoldProductsDTO =
                this.userRepository
                        .findAllBySellingProductsBuyerIsNotNullOrderByLastNameAscFirstNameAsc()
                        .orElseThrow(() -> new NoSuchElementException(NO_USERS_WITH_SOLD_PRODUCTS))
                        .stream()
                        .map(user -> this.mapper.map(user, UserWithSoldProductDTO.class))
                        .toList();

        writeJsonIntoFile(userWithSoldProductsDTO, USER_WITH_SOLD_PRODUCTS_FILE_PATH);

        return USERS_WITH_SOLD_PRODUCTS_SAVED_SUCCESSFULLY;
    }

    @Override
    public String findUsersWithSoldProductsAndCount() throws IOException {

        final List<UserWithProductsDTO> userWithProductsDTOS = this.userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderByLastNameAscFirstNameAsc()
                .orElseThrow(() -> new NoSuchElementException(NO_DATA_IN_FOR_USERS))
                .stream()
                .map(user -> this.mapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO)
                .toList();

        writeJsonIntoFile( new UsersWithProductsWrapperDTO(userWithProductsDTOS), USERS_AND_PRODUCTS_FILE_PATH);

        return USERS_AND_PRODUCTS_SAVED_SUCCESSFULLY;
    }
}
