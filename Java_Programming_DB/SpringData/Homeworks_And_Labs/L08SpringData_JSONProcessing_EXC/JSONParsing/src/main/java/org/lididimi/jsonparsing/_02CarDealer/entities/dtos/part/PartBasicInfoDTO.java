package org.lididimi.jsonparsing._02CarDealer.entities.dtos.part;

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
public class PartBasicInfoDTO {

    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private BigDecimal price;
}