package com.nmbr.repository;

import com.nmbr.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

     Optional<Employee> findById(Long id);

    void deleteById(Long id);
}
