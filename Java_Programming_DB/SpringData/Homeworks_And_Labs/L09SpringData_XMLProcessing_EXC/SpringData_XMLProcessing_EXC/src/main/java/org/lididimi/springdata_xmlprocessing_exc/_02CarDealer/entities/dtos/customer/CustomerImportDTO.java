package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.config.LocalDateTimeAdapter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDTO {

    @XmlAttribute
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

   /* @XmlElement(name = "birth-date")
    private String birthDate;*/ // leave it as String if we want to use model mapper for converting date

    @XmlElement(name = "is-young-driver")
    private Boolean isYoungDriver;
}