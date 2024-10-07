package org.lididimi.modelmapper.dtos.addresses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO extends CreateAddressDTO{

    private Long id;
}
