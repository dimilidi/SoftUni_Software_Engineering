package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.part.wrappers.PartBasicsInfoWrapperDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarDetailedInfoDTO {

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlElement(name = "parts")
    private PartBasicsInfoWrapperDTO parts;
}