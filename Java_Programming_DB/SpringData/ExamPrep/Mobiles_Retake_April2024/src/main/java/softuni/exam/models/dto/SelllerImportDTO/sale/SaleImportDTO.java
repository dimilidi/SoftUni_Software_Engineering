package softuni.exam.models.dto.SelllerImportDTO.sale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleImportDTO {

    private Boolean discounted;

    @Size(min = 7, max = 7)
    private String number;

    private LocalDateTime saleDate; // should be String if date converter is in ModelMapper

    private Long seller;
}
