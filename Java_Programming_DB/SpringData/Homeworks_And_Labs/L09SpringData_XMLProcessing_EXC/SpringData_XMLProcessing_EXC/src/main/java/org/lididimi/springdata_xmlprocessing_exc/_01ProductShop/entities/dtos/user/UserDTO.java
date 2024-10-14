package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductBasicInfoDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductsSoldWithCountDTO;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private int age;

    private Set<UserDTO> friends;

    private Set<ProductDTO> sellingProducts;

    private Set<ProductDTO> boughtProducts;

    public String getFullName() {
        return this.firstName == null ? this.lastName : this.firstName + " " + this.lastName;
    }


    public UserWithProductsDTO toUserWithProductsDTO() {
        return new UserWithProductsDTO(this.firstName, this.lastName, this.age, toProductsSoldWithCountDTO());
    }


    public ProductsSoldWithCountDTO toProductsSoldWithCountDTO() {
        return new ProductsSoldWithCountDTO(sellingProducts
                .stream()
                .map(this::toProductBasicInfoDTO)
                .toList());
    }

    public ProductBasicInfoDTO toProductBasicInfoDTO(ProductDTO productDTO) {
        return new ProductBasicInfoDTO(productDTO.getName(), productDTO.getPrice());
    }
}