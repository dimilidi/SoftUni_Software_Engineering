package softuni.exam.models.dto.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportJobDto {

    @Size(min = 2, max = 40)
    @NotNull
    @XmlElement(name = "jobTitle")
    private String title;

    @Min(300)
    @NotNull
    @XmlElement
    private Double salary;

    @Min(10)
    @NotNull
    @XmlElement
    private Double hoursAWeek;

    @Size(min = 5)
    @NotNull
    @XmlElement
    private String description;

    @XmlElement(name = "companyId")
    private Long company;
}
