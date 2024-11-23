package softuni.exam.models.dto.offer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.exam.models.dto.agent.AgentXMLImportDTO;
import softuni.exam.models.dto.apartment.ApartmentXMLImportDTO;
import softuni.exam.util.xmlLocalDateAdapter.XmlLocalDateAdapter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {

    @Positive
    @NotNull
    @XmlElement
    private BigDecimal price;

    @NotNull
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @XmlElement
    private LocalDate publishedOn;

    @NotNull
    @XmlElement
    private ApartmentXMLImportDTO apartment;

    @NotNull
    @XmlElement
    private AgentXMLImportDTO agent;
}
