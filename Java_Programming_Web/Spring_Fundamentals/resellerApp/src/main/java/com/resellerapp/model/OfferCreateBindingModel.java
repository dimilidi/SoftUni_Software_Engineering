package com.resellerapp.model;

import com.resellerapp.model.enums.ConditionName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferCreateBindingModel {

    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters")
    private String description;

    @NotNull(message = "The price must be a positive number")
    @Positive(message = "The price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "Condition must be selected")
    private ConditionName condition;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionName getCondition() {
        return condition;
    }

    public void setCondition(ConditionName condition) {
        this.condition = condition;
    }
}
