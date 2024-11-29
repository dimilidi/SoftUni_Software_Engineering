package exam.model.dto.shop;

import exam.model.dto.town.TownBasicImportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {

    @Size(min = 4)
    @NotNull
    private String name;

    @Min(20000)
    @NotNull
    private BigDecimal income;

    @Size(min = 4)
    @NotNull
    private String address;

    @Min(1)
    @Max(50)
    @NotNull
    @XmlElement(name = "employee-count")
    private Integer employeeCount;

    @Min(150)
    @NotNull
    @XmlElement(name = "shop-area")
    private Integer shopArea;

    @NotNull
    private TownBasicImportDTO town;
}
