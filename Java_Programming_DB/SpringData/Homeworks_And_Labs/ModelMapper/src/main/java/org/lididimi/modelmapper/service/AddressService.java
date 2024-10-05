package org.lididimi.modelmapper.service;

import org.lididimi.modelmapper.dtos.AddressDTO;
import org.lididimi.modelmapper.models.Address;

public interface AddressService {

    Address create(AddressDTO data);

}