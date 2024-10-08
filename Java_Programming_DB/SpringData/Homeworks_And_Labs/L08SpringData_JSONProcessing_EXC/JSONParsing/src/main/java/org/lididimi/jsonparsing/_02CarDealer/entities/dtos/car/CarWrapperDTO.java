package org.lididimi.jsonparsing._02CarDealer.entities.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.part.PartBasicInfoDTO;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarWrapperDTO {

    private CarDetailedInfoDTO car;

    private List<PartBasicInfoDTO> parts;
}
