package com.spring.employee.employeeManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.employee.employeeManagement.entity.Employee;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee
, String>  {
	/*public List<Employee> findAll();
	
	public Employee findById(String id);
	
	public void deleteById(String id);
	
	public void saveOrUpdate(Employee employee);*/
	
}