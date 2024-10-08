package org.lididimi.jsonparsing._02CarDealer.entities.dtos.car;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarMakeDTO {

    @SerializedName("Id")
    private Long id;

    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TraveledDistance")
    private Long travelledDistance;
}