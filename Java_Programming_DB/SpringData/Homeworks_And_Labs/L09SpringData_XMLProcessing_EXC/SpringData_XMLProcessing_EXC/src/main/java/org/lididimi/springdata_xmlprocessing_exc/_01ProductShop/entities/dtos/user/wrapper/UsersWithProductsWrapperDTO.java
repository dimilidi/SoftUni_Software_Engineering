package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.wrapper;

import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.UserWithProductsDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.user.UserWithSoldProductDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithProductsWrapperDTO {

    @XmlAttribute( name = "count")
    private long usersCount;

    @XmlElement(name = "user")
    private List<UserWithProductsDTO> users;

    public UsersWithProductsWrapperDTO(List<UserWithProductsDTO> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}