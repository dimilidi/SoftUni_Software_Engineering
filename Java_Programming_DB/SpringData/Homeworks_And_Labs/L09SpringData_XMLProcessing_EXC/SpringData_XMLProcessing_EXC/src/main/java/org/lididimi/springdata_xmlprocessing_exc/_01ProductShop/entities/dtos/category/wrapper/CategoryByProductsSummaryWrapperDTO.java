package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.wrapper;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.CategoryByProductsSummaryDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductSoldDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryByProductsSummaryWrapperDTO {

    @XmlElement(name = "category")
    private List<CategoryByProductsSummaryDTO> categories;


}
