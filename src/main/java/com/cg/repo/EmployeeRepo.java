package com.cg.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Employee;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
