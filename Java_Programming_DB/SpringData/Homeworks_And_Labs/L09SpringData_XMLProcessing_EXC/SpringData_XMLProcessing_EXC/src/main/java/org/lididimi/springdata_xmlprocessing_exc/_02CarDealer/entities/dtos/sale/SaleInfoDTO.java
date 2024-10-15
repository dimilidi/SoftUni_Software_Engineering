package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car.CarBasicInfoDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleInfoDTO {

    private Long id;

    private Double discount;

    private CarBasicInfoDTO car;
}