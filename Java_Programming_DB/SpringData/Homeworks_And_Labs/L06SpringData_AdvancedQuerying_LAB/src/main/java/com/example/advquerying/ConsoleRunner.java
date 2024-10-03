package com.example.advquerying;

import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.example.advquerying.constants.Constants.*;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.print(ENTER_TASK_NUMBER);
        int taskNumber = Integer.parseInt(scanner.nextLine());

        switch (taskNumber) {
            //Task 1 Select Shampoo By Size
            case 1 -> selectShampooBySize();
            //2. Select Shampoos By Size Or Label
            case 2 -> selectShampooBySizeOrLabel();
            //3. Select Shampoos By Price
            case 3 -> selectShampooByPriceHigherThan();
            //4. Select Ingredients By Name
            case 4 -> selectIngredientsStartingWithLetters();
            //5. Select Ingredients By Names
            case 5 -> selectIngredientsByName();
            //6. Count Shampoos By Price
            case 6 -> countShampoosByPrice();
            //7. Select Shampoos By Ingredients
            case 7 -> selectShampooByIngredients();
            //8. Select Shampoos By Ingredients Count
            case 8 -> selectShampoosByIngredientsCount();
            //9. Delete Ingredients By Name
            case 9 -> deleteIngredientsByName();
            //10. Update Ingredients By Price
            case 10 -> updateAllIngredientsPrice();
            //11. Update Ingredients By Names
            case 11 -> updateIngredientsPriceByName();
        }
    }

    private void updateIngredientsPriceByName() {
        //11. Update Ingredients By Names
        final List<String> ingredients =  getIngredientsInAList();

        System.out.print(ENTER_PERCENTAGE);
        String percent = scanner.nextLine();
        percent = String.format(percent.equals("100") ? "2" : String.format("1.%s", percent));
        BigDecimal percentage = BigDecimal.valueOf(Double.parseDouble(percent));

        this.ingredientService.updatePriceByName(ingredients, percentage);

        System.out.println(INGREDIENTS_PRICE_UPDATED);
    }


    private void updateAllIngredientsPrice() {
        //10. Update Ingredients By Price
        System.out.print(ENTER_PERCENTAGE);
        String percent = scanner.nextLine();
        percent = String.format(percent.equals("100") ? "2" : String.format("1.%s", percent));
        BigDecimal percentage = BigDecimal.valueOf(Double.parseDouble(percent));
        this.ingredientService.increasePrice(percentage);

        System.out.println(INGREDIENTS_PRICE_UPDATED);
    }

    private void deleteIngredientsByName() {
        //9. Delete Ingredients By Name
        System.out.print(ENTER_INGREDIENT_NAME);
        String ingredient = scanner.nextLine();

        this.ingredientService.deleteByName(ingredient);

        System.out.println();
    }

    private void selectShampoosByIngredientsCount() {
        //8. Select Shampoos By Ingredients Count
        System.out.print(ENTER_INGREDIENTS_COUNT);
        int count = Integer.parseInt(scanner.nextLine());

        shampooService.findShampoosWithIngredientsCountLessThan(count)
                .forEach(System.out::println);
    }

    private void selectShampooByIngredients() {
        final List<String> ingredients = getIngredientsInAList();

        shampooService
                .findShampoosContainingIngredient(ingredients)
                .forEach(System.out::println);
    }

    private void countShampoosByPrice() {
        //6. Count Shampoos By Price
        System.out.print(ENTER_SHAMPOO_PRICE);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));

        int count = this.shampooService.findShampooCountByPriceLessThan(price);

        System.out.println(count);
    }

    private void selectIngredientsByName() {
        //5. Select Ingredients By Names
        final List<String> ingredients = new ArrayList<>();

        System.out.print(ENTER_INGREDIENT_NAME);
        String ingredient = scanner.nextLine();

        System.out.println(ENTER_END_TO_STOP);

        while (!ingredient.equalsIgnoreCase(END)) {

            ingredients.add(ingredient);

            System.out.print(ENTER_INGREDIENT_NAME);
            ingredient = scanner.nextLine();
        }

        ingredientService
                .findIngredientsByName(ingredients)
                .forEach(System.out::println);
    }

    private void selectIngredientsStartingWithLetters() {
        //4. Select Ingredients By Name
        System.out.print(ENTER_STARTS_WITH_LETTERS_FOR_INGREDIENT);
        String letters = scanner.nextLine();

        ingredientService.
                findIngredientsByNameStartLetters(letters)
                .forEach(System.out::println);
    }

    private void selectShampooByPriceHigherThan() {
        //3. Select Shampoos By Price
        System.out.print(ENTER_SHAMPOO_PRICE);
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(scanner.nextLine()));

        shampooService
                .findShampoosByPriceGreaterThan(price)
                .forEach(System.out::println);
    }

    private void selectShampooBySizeOrLabel() {
        //2. Select Shampoos by Size or Label
        System.out.print(ENTER_SHAMPOO_SIZE);
        String size = scanner.nextLine();
        System.out.print(ENTER_LABEL_ID);
        long label = Long.parseLong(scanner.nextLine());

        shampooService
                .findShampoosBySizeAndLabel(size, label)
                .forEach(System.out::println);
    }

    private void selectShampooBySize() {
        //Task 1 Select Shampoo By Size
        System.out.print(ENTER_SHAMPOO_SIZE);
        String size = scanner.nextLine();

        shampooService
                .findShampoosBySize(size)
                .forEach(System.out::println);
    }

    private static List<String> getIngredientsInAList() {
        final List<String> ingredients = new ArrayList<>();

        System.out.print(ENTER_INGREDIENT_NAME);
        String ingredient = scanner.nextLine();

        System.out.println(ENTER_END_TO_STOP);

        while (!(ingredient.equalsIgnoreCase(END))) {

            ingredients.add(ingredient);

            System.out.print(ENTER_INGREDIENT_NAME);
            ingredient = scanner.nextLine();
        }

        return ingredients;
    }

}
