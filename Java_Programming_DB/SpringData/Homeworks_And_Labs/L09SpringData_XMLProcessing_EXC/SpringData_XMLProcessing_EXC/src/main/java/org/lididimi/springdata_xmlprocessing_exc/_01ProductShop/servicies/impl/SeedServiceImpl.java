package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.Category;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.Product;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.User;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.wrapper.SeedCategoriesWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.wrapper.SeedProductsWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.wrapper.SeedUsersWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories.CategoryRepository;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories.ProductRepository;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories.UserRepository;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;

import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Constants.*;
import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public SeedServiceImpl(
            ModelMapper mapper,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String seedUsers() throws JAXBException {
        if (this.userRepository.count() != 0) {
            return USER_DATA_ALREADY_SEEDED;
        }

        File xml = USER_XML_FILE_PATH.toFile();

        List<User> users = parseAndMapXML(
                xml,
                SeedUsersWrapperDTO.class,
                SeedUsersWrapperDTO::getUsers,
                User.class
        );

        this.userRepository.saveAllAndFlush(users);

        users.forEach(this::setRandomFriends);
        this.userRepository.saveAllAndFlush(users);

        return USER_DATA_SEEDED_SUCCESSFULLY;
    }


    @Override
    public String seedCategories() throws JAXBException {
        if (this.categoryRepository.count() != 0) {
            return CATEGORY_DATA_ALREADY_SEEDED;
        }

        File xml = CATEGORIES_XML_FILE_PATH.toFile();

        List<Category> categories = parseAndMapXML(
                xml,
                SeedCategoriesWrapperDTO.class,
                SeedCategoriesWrapperDTO::getCategories,
                Category.class
        );
        this.categoryRepository.saveAllAndFlush(categories);

        return CATEGORY_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedProducts() throws IOException, JAXBException {
        if (this.productRepository.count() != 0) {
            return PRODUCT_DATA_ALREADY_SEEDED;
        }

        File xml = PRODUCTS_XML_FILE_PATH.toFile();

        List<Product> products = parseAndMapXML(
                xml,
                SeedProductsWrapperDTO.class,
                SeedProductsWrapperDTO::getProducts,
                Product.class
        ).stream()
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .toList();

        this.productRepository.saveAllAndFlush(products);

        return PRODUCT_DATA_SEEDED_SUCCESSFULLY;
    }


    private Product setRandomCategories(Product product) {
        Set<Category> categories =
                this.categoryRepository.getRandomEntity().orElseThrow(NoSuchFieldError::new);
        product.setCategories(categories);

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getSeller().getId() % 4 != 0) {

            User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

            while (Objects.equals(buyer.getId(), product.getSeller().getId())) {
                buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
            }

            product.setBuyer(buyer);
        } /*else {
            product.setBuyer(null);
        }*/

        return product;
    }

    private Product setRandomSeller(Product product) {
        final User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
        product.setSeller(seller);
        return product;
    }


    private User getRandomUniqueFriend(Long userId, Set<Long> excludedIds) {
        User randomFriend;

        do {
            randomFriend = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
        } while (excludedIds.contains(randomFriend.getId()));

        return randomFriend;
    }


    private User setRandomFriends(User user) {
        Set<User> friends = new HashSet<>();

        // Set to keep track of excluded IDs (the user's ID and other friends' IDs)
        Set<Long> excludedIds = new HashSet<>();
        excludedIds.add(user.getId());

        // Get the first random friend
        User friendOne = getRandomUniqueFriend(user.getId(), excludedIds);
        friends.add(friendOne);
        excludedIds.add(friendOne.getId());

        // Get the second random friend
        User friendTwo = getRandomUniqueFriend(user.getId(), excludedIds);
        friends.add(friendTwo);

        // Set the friends for the user
        user.setFriends(friends);

        return user;
    }


    private <T, D, E> List<E> parseAndMapXML(File xmlFile, Class<T> wrapperClass, Function<T, List<D>> getDtoListFunction, Class<E> entityClass) throws JAXBException {
        // Create JAXB context and unmarshaller for the wrapper class
        JAXBContext context = JAXBContext.newInstance(wrapperClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Unmarshal the XML file into the wrapper class instance
        T wrapperDTO = (T) unmarshaller.unmarshal(xmlFile);

        // Extract the list of individual DTOs from the wrapper DTO using the passed function
        List<D> dtoList = getDtoListFunction.apply(wrapperDTO);

        // Map each DTO to the target entity class
        return dtoList.stream()
                .map(dto -> mapper.map(dto, entityClass))
                .toList();
    }


}