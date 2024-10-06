package org.lididimi.modelmapper.service;

import org.lididimi.modelmapper.dtos.*;
import org.lididimi.modelmapper.models.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(long id);

    EmployeeDTO findEmployee(Long id);

    ManagerDTO findManager(Long id);

    List<ManagerDTO> findAllManagers();

}
