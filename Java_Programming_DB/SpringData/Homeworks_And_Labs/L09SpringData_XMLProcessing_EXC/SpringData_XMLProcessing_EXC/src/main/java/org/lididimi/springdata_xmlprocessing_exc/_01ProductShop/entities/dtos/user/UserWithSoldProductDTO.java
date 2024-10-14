package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.wrapper.ProductsSoldWrapperDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapperDTO sellingProducts;
}