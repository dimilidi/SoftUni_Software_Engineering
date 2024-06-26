package bg.softuni.shoppinglist.model.dto;

import bg.softuni.shoppinglist.model.enums.CategoryNameEnum;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddDTO {
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters")
    @NotNull(message = "Name cannot be empty")
    private String name;

    @Size(min = 5, message = "Description min length must be minimum 5(inclusive) characters")
    private String description;

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDateTime neededBefore;

    @Positive(message = "Price must be positive")
   // @DecimalMin(value =  "0", message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "You must select category")
    private CategoryNameEnum category;

    public ProductAddDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNeededBefore() {
        return neededBefore;
    }

    public void setNeededBefore(LocalDateTime neededBefore) {
        this.neededBefore = neededBefore;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryNameEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryNameEnum category) {
        this.category = category;
    }
}
