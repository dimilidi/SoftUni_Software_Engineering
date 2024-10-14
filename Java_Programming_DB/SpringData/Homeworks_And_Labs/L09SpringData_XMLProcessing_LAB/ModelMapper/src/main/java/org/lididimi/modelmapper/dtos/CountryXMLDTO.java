package org.lididimi.modelmapper.dtos;

import jakarta.xml.bind.annotation.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXMLDTO {

    @XmlAttribute
    //@XmlElement
    private String name;
}