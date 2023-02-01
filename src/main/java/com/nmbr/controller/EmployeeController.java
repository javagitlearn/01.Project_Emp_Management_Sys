package com.nmbr.controller;

import com.nmbr.entities.Employee;
import com.nmbr.services.EmployeeService;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
   @Autowired
    private EmployeeService employeeService;

    // get all employee controller
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
       return  new ResponseEntity<List<Employee>>(employeeService.findAllEmployee(), HttpStatus.OK);
    }
    // get single employee controller
    @GetMapping("search/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable("id")  Long id ){
        return  new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.FOUND);
    }

    // create employee controller
    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
      return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    // update employee controller
    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee, @PathVariable("id") Long id){
        employeeService.updateEmployee(employee,id);
        return new ResponseEntity<>( HttpStatus.ACCEPTED);
    }

    // delete controller
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteById(  @PathVariable("id")  Long id){
        employeeService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // import controller

    @Autowired
   private JobLauncher jobLauncher;
    @Autowired
   private Job job;

    @PostMapping("/importCsv")
    public void importCsvFile(){
        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("StartAt",System.currentTimeMillis()).toJobParameters();

        try {
            jobLauncher.run(job,jobParameter);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}
