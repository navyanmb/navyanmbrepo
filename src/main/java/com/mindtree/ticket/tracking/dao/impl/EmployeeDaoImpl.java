package com.mindtree.ticket.tracking.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.ticket.tracking.dao.EmployeeDao;
import com.mindtree.ticket.tracking.model.Employee;
import com.mindtree.ticket.tracking.repository.EmployeeRepository;

@Service
public class EmployeeDaoImpl implements EmployeeDao{
	
	@Autowired
	EmployeeRepository empRepository;

	@Override
	public Optional<Employee> findEmployeeById(String id) {
		return empRepository.findById(id);
	}

}
