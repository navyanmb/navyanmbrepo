package com.mindtree.ticket.tracking.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private String mid;
	private String employee_name;
	private Date hire_date;
	private String department;
	
	public Employee()
	{
		
	}
	public Employee(String mid, String employee_name, Date hire_date, String department) {
		super();
		this.mid = mid;
		this.employee_name = employee_name;
		this.hire_date = hire_date;
		this.department = department;
	}
	public String getmId() {
		return mid;
	}
	public void setmId(String mid) {
		this.mid = mid;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "Employee [mId=" + mid + ", employee_name=" + employee_name + ", hire_date=" + hire_date
				+ ", department=" + department + "]";
	}	
}
