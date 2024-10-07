package org.lididimi.jsonparsing._01ProductShop.servicies.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.lididimi.jsonparsing._01ProductShop.entities.Category;
import org.lididimi.jsonparsing._01ProductShop.entities.Product;
import org.lididimi.jsonparsing._01ProductShop.entities.User;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.category.SeedCategoryDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.product.SeedProductDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.SeedUserDTO;
import org.lididimi.jsonparsing._01ProductShop.repositories.CategoryRepository;
import org.lididimi.jsonparsing._01ProductShop.repositories.ProductRepository;
import org.lididimi.jsonparsing._01ProductShop.repositories.UserRepository;
import org.lididimi.jsonparsing._01ProductShop.servicies.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.*;

import static org.lididimi.jsonparsing._01ProductShop.constants.Constants.*;
import static org.lididimi.jsonparsing._01ProductShop.constants.Paths.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public SeedServiceImpl(
            Gson gson,
            ModelMapper mapper,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.gson = gson;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public String seedUsers() throws IOException {
        if (this.userRepository.count() != 0) {
            return USER_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(USER_JSON_FILE_PATH);
        List<User> users = parseJsonToList(json, SeedUserDTO[].class, User.class);
        this.userRepository.saveAllAndFlush(users);

        users.forEach(this::setRandomFriends);
        this.userRepository.saveAllAndFlush(users);

        return USER_DATA_SEEDED_SUCCESSFULLY;
    }


    @Override
    public String seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return CATEGORY_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(CATEGORIES_JSON_FILE_PATH);
        List<Category> categories = parseJsonToList(json, SeedCategoryDTO[].class, Category.class);
        this.categoryRepository.saveAllAndFlush(categories);

        return CATEGORY_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedProducts() throws IOException {
        if (this.productRepository.count() != 0) {
            return PRODUCT_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(PRODUCTS_JSON_FILE_PATH);
        List<Product> products = parseJsonToList(json, SeedProductDTO[].class, Product.class)
                .stream()
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategories)
                .toList();

        this.productRepository.saveAllAndFlush(products);

        return PRODUCT_DATA_SEEDED_SUCCESSFULLY;
    }

    private <T, R> List<R> parseJsonToList(String json, Class<T[]> dtoClass, Class<R> entityClass) {
        // Parse JSON into array of DTOs
        T[] dtoArray = this.gson.fromJson(json, dtoClass);

        // Map each DTO to the target entity class (e.g., User.class)
        return Arrays.stream(dtoArray)
                .map(dto -> this.mapper.map(dto, entityClass))
                .toList();
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

    private User setRandomFriends(User user) {

        final Set<User> friends = new HashSet<>();

       /* User friendOne = this.userRepository.getRandomEntity().get();

        while (Objects.equals(user.getId(), friendOne.getId())) {
            friendOne = this.userRepository.getRandomEntity().get();
        }

        User friendTwo = this.userRepository.getRandomEntity().get();

        while (Objects.equals(user.getId(), friendTwo.getId())
                && (Objects.equals(friendOne.getId(), friendTwo.getId()))) {
            friendTwo = this.userRepository.getRandomEntity().get();
        }

        friends.add(friendOne);
        friends.add(friendTwo);

        user.setFriends(friends);
*/
        return user;
    }
}