package org.lididimi.jsonparsing._01ProductShop.entities.dtos.user;

import org.lididimi.jsonparsing._01ProductShop.entities.dtos.product.ProductSoldDTO;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSoldProductDTO {

    private String firstName;

    private String lastName;

    private List<ProductSoldDTO> sellingProducts;

}