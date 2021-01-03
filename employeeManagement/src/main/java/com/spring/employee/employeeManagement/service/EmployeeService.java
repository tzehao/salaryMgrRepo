package com.spring.employee.employeeManagement.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.entity.EmployeePage;
import com.spring.employee.employeeManagement.entity.EmployeeSearchCriteria;

public interface EmployeeService {

	public Page<Employee> getEmployees(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria);
	
	public Employee addEmployee(Employee employee);
		
	/*public List<Employee> findAll();
	
	public Employee findById(String id);
	
	public void deleteById(String id);
	
	public void saveOrUpdate(Employee employee);*/
}
