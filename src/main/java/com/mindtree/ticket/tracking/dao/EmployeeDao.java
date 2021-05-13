package com.mindtree.ticket.tracking.dao;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mindtree.ticket.tracking.model.Employee;

@Service
public interface EmployeeDao {
	
	public Optional<Employee> findEmployeeById(String id);

}
