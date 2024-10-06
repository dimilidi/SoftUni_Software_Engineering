package org.lididimi.modelmapper.dtos;

import java.math.BigDecimal;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private BigDecimal salary;

    @Override
    public String toString() {
        return String.format("%s %s %.2f ", this.firstName, this.lastName, this.salary);
    }
}