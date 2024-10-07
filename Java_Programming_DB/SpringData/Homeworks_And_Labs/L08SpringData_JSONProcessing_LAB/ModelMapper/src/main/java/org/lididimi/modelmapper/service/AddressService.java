package org.lididimi.modelmapper.service;

import org.lididimi.modelmapper.dtos.addresses.CreateAddressDTO;
import org.lididimi.modelmapper.models.Address;

public interface AddressService {

    Address create(CreateAddressDTO data);

}