package com.udacity.jdnd.course3.critter.repository;

import javax.transaction.Transactional;
import com.udacity.jdnd.course3.critter.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
