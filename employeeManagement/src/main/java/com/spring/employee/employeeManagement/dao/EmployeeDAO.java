package com.spring.employee.employeeManagement.dao;

import java.util.List;

import com.spring.employee.employeeManagement.entity.Employee;

public interface EmployeeDAO {
	public List<Employee> findAll();
	
	public Employee findById(String id);
	
	public void deleteById(String id);
	
	public void saveOrUpdate(Employee employee);
	
}