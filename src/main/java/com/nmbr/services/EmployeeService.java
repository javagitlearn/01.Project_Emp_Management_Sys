package com.nmbr.services;

import com.nmbr.entities.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    // get all employee details
    List<Employee> findAllEmployee();

    // get single employee details by id

    Optional<Employee> findEmployeeById(Long id);

    // create new Employee

    public Employee createEmployee(Employee employee);


    // update employee by id  service

    public void updateEmployee(Employee employee, Long id);

    // Delete Employee by id

    public void deleteById(Long id);

}
