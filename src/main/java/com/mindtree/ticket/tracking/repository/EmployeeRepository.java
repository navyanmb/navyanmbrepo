package com.mindtree.ticket.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ticket.tracking.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String>{
	
}
