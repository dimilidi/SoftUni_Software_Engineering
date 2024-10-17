package softuni.exam.models.dto.volcanologist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.entity.Volcano;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "volcanologist")
@XmlAccessorType(XmlAccessType.FIELD)
public class VolcanologistImportDTO {

    @Size(min = 2, max = 30)
    @NotNull
    @XmlElement(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    @XmlElement(name = "last_name")
    private String lastName;

    @Positive
    @NotNull
    @XmlElement
    private Double salary;

    @Min(18)
    @Max(80)
    @NotNull
    @XmlElement
    private Integer age;

    @XmlElement(name = "exploring_from")
    private Date exploringFrom;

    @XmlElement(name = "exploring_volcano_id")
    private Long exploringVolcano;
}


