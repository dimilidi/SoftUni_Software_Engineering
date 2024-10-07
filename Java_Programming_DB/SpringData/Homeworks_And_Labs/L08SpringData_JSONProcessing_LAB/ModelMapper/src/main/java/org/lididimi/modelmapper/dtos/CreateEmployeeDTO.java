package org.lididimi.modelmapper.dtos;

import com.google.gson.annotations.Expose;
import lombok.*;
import org.lididimi.modelmapper.dtos.addresses.CreateAddressDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CreateEmployeeDTO {

    @Expose
    private String firstName;
    private String lastName;
    @Expose
    private BigDecimal salary;
    @Expose
    private LocalDate birthday;
    @Expose
    private CreateAddressDTO address;

}