package com.spring.employee.employeeManagement.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.entity.EmployeePage;
import com.spring.employee.employeeManagement.entity.EmployeeSearchCriteria;
import com.spring.employee.employeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/users")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	/*@GetMapping("/usersAll")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/usersGetId")
	public Employee getEmployeeGetById(@RequestParam String id) {
		Employee employee = employeeService.findById(id);
		if(employee == null) {
			throw new SearchNotFoundException("Employee id not found - " + id);
		}
		
		return employee;
	}
	
	@PostMapping("/users")
	public Employee saveEmployee(@RequestBody Employee employee) {
		employee.setId("9999id");
		employeeService.saveOrUpdate(employee);
		
		return employee;
	}
	
	@PutMapping("/users")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.saveOrUpdate(employee);
		return employee;	
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteEmployee(@PathVariable String id) {
		Employee employee = employeeService.findById(id);
		if(employee == null) {
			throw new SearchNotFoundException("Employee id not found - " + id);
		}
		
		employeeService.deleteById(id);
		return "Deleted Employee with id - " + id;
	}*/

	@GetMapping("")
	public ResponseEntity<Page<Employee>> getEmployees(@RequestParam double minSalary, double maxSalary, int offset, int limit, String sort) {
		String plusMinus = String.valueOf(sort.charAt(0));
		Direction sortDirection;
		if(plusMinus.equals("+"))
			sortDirection = Sort.Direction.ASC;
		else if(plusMinus.equals("-"))
			sortDirection = Sort.Direction.DESC;
		else
			throw new SearchNotFoundException("Incorrect sort input - " + sort);
		String sortBy = sort.substring(1);
		
		EmployeePage employeePage = new EmployeePage(offset, limit, sortDirection, sortBy);
		EmployeeSearchCriteria employeeSearchCriteria = new EmployeeSearchCriteria(minSalary, maxSalary);
		return new ResponseEntity<>(employeeService.getEmployees(employeePage, employeeSearchCriteria), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestParam String id, String login, String name, double salary) {
		Employee e = new Employee(id, login, name, salary);
		return new ResponseEntity<>(employeeService.addEmployee(e), HttpStatus.OK);
	}
}
