package org.lididimi.jsonparsing._02CarDealer.entities.dtos.customer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.sale.SaleInfoDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfoOrderedDTO {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private String birthDate;

    @SerializedName("IsYoungerDriver")
    private Boolean isYoungDriver;

    @SerializedName("Sales")
    private List<SaleInfoDTO> sales;

}