package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarBasicInfoDTO {

    private String make;

    private String model;

    private Long travelledDistance;
}