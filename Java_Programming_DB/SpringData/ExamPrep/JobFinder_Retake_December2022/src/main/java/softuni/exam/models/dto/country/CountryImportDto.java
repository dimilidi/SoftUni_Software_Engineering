package softuni.exam.models.dto.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryImportDto {
    @Size(min = 2, max = 30)
    @NotNull
    private String name;

    @Size(min = 2, max = 19)
    @NotNull
    private String countryCode;

    @Size(min = 2, max = 19)
    @NotNull
    private String currency;
}
