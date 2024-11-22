package softuni.exam.models.dto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.util.xmlLocalDateAdapter.XmlLocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompanyImportDto {
    @Size(min = 2, max = 40)
    @NotNull
    @XmlElement(name = "companyName")
    private String name;

    @Size(min = 2, max = 30)
    @NotNull
    @XmlElement
    private String website;

    @NotNull
    @XmlElement
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    private LocalDate dateEstablished;

    @XmlTransient
    private Long countryId;
}
