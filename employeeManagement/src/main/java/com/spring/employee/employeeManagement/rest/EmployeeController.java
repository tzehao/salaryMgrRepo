package com.spring.employee.employeeManagement.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/usersAll")
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
	}
}
