package org.lididimi.modelmapper.dtos;

import lombok.*;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "addressXML")
// XmlAccessType.FIELD: do not need getters, setters;
// it is not mandatory fields to be annotated as xml elements
// takes all fields unless annotated with @XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
// @XmlAccessorType(XmlAccessType.PROPERTY) // needs getters & setters or annotated fields
public class AddressWithCollectionDTO {

    @XmlAttribute
    private int id;

    @XmlElementWrapper(name = "countries")
    private List<CountryXMLDTO> country;

    @XmlElement(name = "city")
    // @XmlTransient
    private String city;

    public AddressWithCollectionDTO(int id, CountryXMLDTO country, String city) {
        this.id = id;
        this.country = List.of(country, country, country);
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "countries=" + country +
                ", city='" + city + '\'' +
                '}';
    }
}
