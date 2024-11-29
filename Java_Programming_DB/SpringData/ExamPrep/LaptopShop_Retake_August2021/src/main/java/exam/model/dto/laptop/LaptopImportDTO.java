package exam.model.dto.laptop;

import exam.model.dto.shop.ShopBaseImportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaptopImportDTO {

    @Size(min = 9)
    @NotNull
    private String macAddress;

    @Positive
    @NotNull
    private Float cpuSpeed;

    @Min(8)
    @Max(128)
    @NotNull
    private Integer ram;

    @Min(128)
    @Max(1024)
    @NotNull
    private Integer storage;

    @NotNull
    @Size(min = 10)
    private String description;

    @Positive
    @NotNull
    private Double price;

    @NotNull
    private String warrantyType;

    @NotNull
    private ShopBaseImportDTO shop;
}
