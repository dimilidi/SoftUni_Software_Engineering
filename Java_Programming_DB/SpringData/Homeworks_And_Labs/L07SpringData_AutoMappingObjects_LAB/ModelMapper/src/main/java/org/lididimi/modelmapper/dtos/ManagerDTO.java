package org.lididimi.modelmapper.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.lididimi.modelmapper.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ManagerDTO {

    private String firstName;
    private String lastName;
    private List<EmployeeDTO> inChargeOfEmployees;

    public ManagerDTO() {
        this.inChargeOfEmployees = new ArrayList<>();
    }

    @Override
    public String toString() {

        String employeesStr = inChargeOfEmployees
                .stream()
                .map(e -> " - " + e.toString())
                .collect(Collectors.joining("\n"));

        return String.format("%s %s | Employees: %d%n%s ", this.firstName, this.lastName, this.inChargeOfEmployees.size(), employeesStr);
    }
}
