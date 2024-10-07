package org.lididimi.jsonparsing._01ProductShop.entities.dtos.user;

import com.google.gson.annotations.SerializedName;
import org.lididimi.jsonparsing._01ProductShop.entities.dtos.product.ProductsSoldWithCountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithProductsDTO {

    private String firstName;

    private String lastName;

    private int age;

    @SerializedName("soldProducts")
    private ProductsSoldWithCountDTO products;
}