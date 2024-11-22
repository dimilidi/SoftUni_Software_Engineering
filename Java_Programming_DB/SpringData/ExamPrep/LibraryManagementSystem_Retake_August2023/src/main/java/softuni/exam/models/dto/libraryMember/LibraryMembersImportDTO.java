package softuni.exam.models.dto.libraryMember;

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
public class LibraryMembersImportDTO {

    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @Size(min = 2, max = 40)
    private String address;

    @Size(min = 2, max = 20)
    @NotNull
    private String phoneNumber;
}
