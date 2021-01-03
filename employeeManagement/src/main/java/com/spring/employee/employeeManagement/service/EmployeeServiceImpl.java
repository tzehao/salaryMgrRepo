package com.spring.employee.employeeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.employee.employeeManagement.dao.EmployeeDAO;
import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.entity.EmployeePage;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	public Page<Employee> getEmployee(EmployeePage employeePage) {
		Sort sort = Sort.by(employeePage.getSortDirection(), employeePage.getSortBy());
		Pageable pageable = PageRequest.of(employeePage.getPageNumber(), employeePage.getPageSize(), sort);
		
		return employeeDAO.findAll(pageable);
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeDAO.save(employee);
	}
	
	/*@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findById(String id) {
		return employeeDAO.findById(id);
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		employeeDAO.deleteById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Employee employee) {
		employeeDAO.saveOrUpdate(employee);
	}*/

}
