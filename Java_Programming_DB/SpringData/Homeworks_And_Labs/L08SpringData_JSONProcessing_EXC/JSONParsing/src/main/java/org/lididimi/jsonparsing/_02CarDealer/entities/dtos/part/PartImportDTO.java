package org.lididimi.jsonparsing._02CarDealer.entities.dtos.part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartImportDTO {

    private String name;

    private BigDecimal price;

    private Long quantity;
}