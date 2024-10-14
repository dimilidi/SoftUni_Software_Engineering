package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.wrapper;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.SeedUserDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeedUsersWrapperDTO {

    @XmlElement(name = "user")
    private List<SeedUserDTO> users;
}