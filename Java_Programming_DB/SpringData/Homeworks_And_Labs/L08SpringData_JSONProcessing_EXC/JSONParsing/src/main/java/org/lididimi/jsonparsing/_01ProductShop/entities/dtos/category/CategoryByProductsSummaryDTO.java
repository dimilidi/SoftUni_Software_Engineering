package org.lididimi.jsonparsing._01ProductShop.entities.dtos.category;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryByProductsSummaryDTO {

    // @SerializedName("name")
    private String name;

    private long productsCount;

    private double averagePrice;

    private BigDecimal totalRevenue;
}