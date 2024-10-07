package org.lididimi.modelmapper.dtos.addresses;

import com.google.gson.annotations.Expose;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateAddressDTO {

    @Expose
    private String country;

    @Expose
    private String city;
}