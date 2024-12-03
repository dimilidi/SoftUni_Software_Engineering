package softuni.exam.models.dto;

import jakarta.validation.constraints.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.xmlLocalDateAdapter.XmlLocalDateAdapter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "personal_data")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalDataImportDTO {
    @Min(1)
    @Max(100)
    @XmlElement
    private Integer age;

    @Past
    @XmlElement(name = "birth_date")
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate birthDate;

    @Size(min = 9, max = 9)
    @NotNull
    @XmlElement(name = "card_number")
    private String cardNumber;

    @Pattern(regexp = "M|F")
    @XmlElement
    private String gender;
}
