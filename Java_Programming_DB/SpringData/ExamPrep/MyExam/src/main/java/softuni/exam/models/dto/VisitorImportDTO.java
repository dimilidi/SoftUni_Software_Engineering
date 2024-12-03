package softuni.exam.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "visitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorImportDTO {

    @Size(min = 2, max = 20)
    @NotNull
    @XmlElement(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 20)
    @NotNull
    @XmlElement(name = "last_name")
    private String lastName;

    @NotNull
    @XmlElement(name = "attraction_id")
    private Long attraction;

    @NotNull
    @XmlElement(name = "country_id")
    private Long country;

    @NotNull
    @XmlElement(name = "personal_data_id")
    private Long personalData;
}
