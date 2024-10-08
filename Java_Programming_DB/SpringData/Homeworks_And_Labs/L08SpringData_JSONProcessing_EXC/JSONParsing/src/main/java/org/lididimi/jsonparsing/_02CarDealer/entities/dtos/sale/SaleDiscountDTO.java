package org.lididimi.jsonparsing._02CarDealer.entities.dtos.sale;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarDetailedInfoDTO;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDiscountDTO {

    @SerializedName("car")
    private CarDetailedInfoDTO car;

    @SerializedName("customerName")
    private String customerName;

    @SerializedName("Discount")
    private Double discount;

    @SerializedName("price")
    private BigDecimal price;

    @SerializedName("priceWithDiscount")
    private BigDecimal priceWithDiscount;
}