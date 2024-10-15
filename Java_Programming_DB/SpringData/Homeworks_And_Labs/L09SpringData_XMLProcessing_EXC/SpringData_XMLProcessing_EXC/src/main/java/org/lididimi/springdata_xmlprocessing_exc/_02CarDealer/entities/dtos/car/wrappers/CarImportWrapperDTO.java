package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car.wrappers;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car.CarImportDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportWrapperDTO {

    @XmlElement(name = "car")
    private List<CarImportDTO> cars;
}
