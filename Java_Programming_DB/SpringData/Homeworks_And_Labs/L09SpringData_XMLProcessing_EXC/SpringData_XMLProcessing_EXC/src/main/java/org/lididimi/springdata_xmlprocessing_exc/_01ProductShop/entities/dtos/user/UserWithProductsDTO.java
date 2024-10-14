package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user;

import jakarta.xml.bind.annotation.*;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithProductsDTO {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private int age;

    @XmlElement(name = "sold-products")
    private ProductsSoldWithCountDTO products;
}