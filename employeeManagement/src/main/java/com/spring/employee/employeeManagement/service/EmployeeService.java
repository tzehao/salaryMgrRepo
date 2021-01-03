package com.spring.employee.employeeManagement.service;

import org.springframework.data.domain.Page;

import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.entity.EmployeePage;
import com.spring.employee.employeeManagement.entity.EmployeeSearchCriteria;

public interface EmployeeService {

	public Page<Employee> getEmployees(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria);
	
	public Employee addEmployee(Employee employee);
}
