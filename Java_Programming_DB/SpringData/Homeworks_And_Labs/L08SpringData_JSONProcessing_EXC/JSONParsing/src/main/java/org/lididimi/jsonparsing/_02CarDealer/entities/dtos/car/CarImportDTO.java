package org.lididimi.jsonparsing._02CarDealer.entities.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarImportDTO {

    private String make;

    private String model;

    private Long travelledDistance;
}