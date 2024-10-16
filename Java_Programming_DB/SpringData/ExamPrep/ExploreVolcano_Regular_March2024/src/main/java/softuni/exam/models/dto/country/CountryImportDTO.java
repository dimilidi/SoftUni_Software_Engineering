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
public class CountryImportDTO {

    @Size(min = 3, max = 30)
    @NotNull
    private String name;

    @Size(min = 3, max = 30)
    private String capital;
}


