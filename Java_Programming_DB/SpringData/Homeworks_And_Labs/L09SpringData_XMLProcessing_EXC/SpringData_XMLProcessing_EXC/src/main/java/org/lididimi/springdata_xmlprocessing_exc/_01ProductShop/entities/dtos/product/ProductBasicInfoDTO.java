package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductBasicInfoDTO {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;
}
