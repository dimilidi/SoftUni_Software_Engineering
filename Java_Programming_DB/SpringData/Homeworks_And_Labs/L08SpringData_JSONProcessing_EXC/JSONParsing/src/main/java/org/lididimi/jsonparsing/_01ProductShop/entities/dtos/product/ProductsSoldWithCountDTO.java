package org.lididimi.jsonparsing._01ProductShop.entities.dtos.product;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductsSoldWithCountDTO {

    private int count;

    private List<ProductBasicInfoDTO> products;

    public ProductsSoldWithCountDTO(List<ProductBasicInfoDTO> products) {
        this.products = products;
        this.count = products.size();
    }
}
