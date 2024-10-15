package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.part.wrappers;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.part.PartImportDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportWrapperDTO {

    @XmlElement(name = "part")
    private List<PartImportDTO> parts;
}
