package org.lididimi.jsonparsing._02CarDealer.entities.dtos.supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierImportDTO {

    private String name;

    private Boolean isImporter;
}