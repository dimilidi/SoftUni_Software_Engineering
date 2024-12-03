package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionImportDTO {
    @Size(min = 5, max = 40)
    @NotNull
    private String name;

    @Size(min = 10, max = 100)
    @NotNull
    private String description;

    @PositiveOrZero
    @NotNull
    private Integer elevation;

    @Size(min = 3, max = 30)
    @NotNull
    private String type;

    @NotNull
    private Long country;

}
