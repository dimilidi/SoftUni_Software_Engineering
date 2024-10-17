package softuni.exam.models.dto.volcano;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolcanoImportDTO {

    @Size(min = 2, max = 30)
    @NotNull
    private String name;

    @Positive
    @NotNull
    private Integer elevation;

    private String volcanoType;

    @NotNull
    private Boolean isActive;

    private Date lastEruption;

    private Long country;
}

