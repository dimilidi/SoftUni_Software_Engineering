package softuni.exam.models.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.enums.GenreTypeEnum;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookImportDTO {
    @Size(min = 3, max = 40)
    @NotNull
    private String title;

    @Size(min = 3, max = 40)
    @NotNull
    private String author;

    @Size(min = 5)
    @NotNull
    private String description;

    @NotNull
    private Boolean available;

    @NotNull
    private GenreTypeEnum genre;

    @Positive
    @NotNull
    private Double rating;
}
