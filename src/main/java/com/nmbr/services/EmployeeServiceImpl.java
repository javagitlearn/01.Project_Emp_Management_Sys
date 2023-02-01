package com.nmbr.services;

import com.nmbr.entities.Employee;
import com.nmbr.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements  EmployeeService{

    @Autowired
    private  EmployeeRepo employeeRepo;

    // get all employee service
    public List<Employee> findAllEmployee(){
        List<Employee> all =employeeRepo.findAll();
        return  all;
    }

    // get by id service
    public Optional<Employee> findEmployeeById(Long id){
           Optional<Employee> singleEmp= employeeRepo.findById(id);
       return Optional.of(singleEmp.get());
    }
    // create new employee service
    public Employee createEmployee(Employee employee) {
    		Employee save = employeeRepo.save(employee);
    	return save;
    	}

    // update employee by id service
    public void updateEmployee(Employee employee, Long id) {
        employee.setId(id);
        employeeRepo.save(employee);
    }

    // delete employee by id service
    public void deleteById(Long id) {

        employeeRepo.deleteById(id);
    }

}
