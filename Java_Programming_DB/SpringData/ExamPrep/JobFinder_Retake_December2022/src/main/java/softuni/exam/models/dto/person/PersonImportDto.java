package softuni.exam.models.dto.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonImportDto {
    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Size(min = 2, max = 13)
    private String phone;

    @NotNull
    private String statusType;

    @NotNull
    private Long country;
}
