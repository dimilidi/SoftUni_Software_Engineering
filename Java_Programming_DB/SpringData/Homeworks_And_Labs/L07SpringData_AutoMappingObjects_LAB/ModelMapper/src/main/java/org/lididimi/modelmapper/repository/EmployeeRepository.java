package org.lididimi.modelmapper.repository;

import org.lididimi.modelmapper.dtos.EmployeeNamesDTO;
import org.lididimi.modelmapper.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new org.lididimi.modelmapper.dtos.EmployeeNamesDTO(e.firstName, e.lastName) " +
            "FROM Employee AS e WHERE e.id = ?1")
    EmployeeNamesDTO findNamesById(long id);
}
