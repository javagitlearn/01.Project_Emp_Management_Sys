package com.nmbr.config;

import com.nmbr.entities.Employee;
import org.springframework.batch.item.ItemProcessor;


public class EmployeeItemProcessor implements ItemProcessor<Employee , Employee> {

    @Override
    public Employee process(Employee employee) throws Exception {

        return employee;
    }
}
