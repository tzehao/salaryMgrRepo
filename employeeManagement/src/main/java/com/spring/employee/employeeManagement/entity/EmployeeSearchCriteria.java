package com.spring.employee.employeeManagement.entity;

import org.springframework.data.domain.Sort;

public class EmployeeSearchCriteria {
	private double minSalary;
	private double maxSalary;
	
	//constructors
	public EmployeeSearchCriteria() {
		
	}
	public EmployeeSearchCriteria(double minSalary, double maxSalary) {
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
	public double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(double maxSalary) {
		this.maxSalary = maxSalary;
	}
}
