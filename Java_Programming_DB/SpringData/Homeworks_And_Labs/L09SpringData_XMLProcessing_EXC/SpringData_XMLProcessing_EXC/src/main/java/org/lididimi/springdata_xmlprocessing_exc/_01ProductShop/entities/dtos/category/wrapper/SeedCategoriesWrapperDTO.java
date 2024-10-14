package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.wrapper;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.SeedCategoryDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedCategoriesWrapperDTO {

    @XmlElement(name = "category")
    private List<SeedCategoryDTO> categories;
}