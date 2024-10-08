package org.lididimi.jsonparsing._02CarDealer.entities.dtos.sale;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarBasicInfoDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfoDTO {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Discount")
    private Double discount;

    @SerializedName("Car")
    private CarBasicInfoDTO car;
}