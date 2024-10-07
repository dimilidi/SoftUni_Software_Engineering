package org.lididimi.jsonparsing._01ProductShop.entities.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.category.CategoryDTO;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.user.UserDTO;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String name;

    private BigDecimal price;

    private UserDTO buyer;

    private UserDTO seller;

    private Set<CategoryDTO> categories;

    public ProductInRangeWithNoBuyerDTO toProductInRangeWithBuyerDTO() {
        return new ProductInRangeWithNoBuyerDTO(name, price, this.seller.getFullName());
    }
}