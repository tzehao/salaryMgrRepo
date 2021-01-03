package com.spring.employee.employeeManagement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.spring.employee.employeeManagement.entity.Employee;
import com.spring.employee.employeeManagement.entity.EmployeePage;
import com.spring.employee.employeeManagement.entity.EmployeeSearchCriteria;

@Repository
public class EmployeeDAOImpl {

	@Autowired
	private EntityManager entityManager;
	
	/*@Override
	public List<Employee> findAll() {
		//get current hibernate session
		Session currentSess = entityManager.unwrap(Session.class);
		
		// Using native hibernate api		
		Query<Employee> query = currentSess.createQuery("from Employee", Employee.class);
		
		//execute
		List<Employee> employeeList = query.getResultList();
		
		return employeeList;
	}

	@Override
	public Employee findById(String id) {
		//get current hibernate session
		Session currentSess = entityManager.unwrap(Session.class);
		
		Employee employee = currentSess.get(Employee.class, id);
		
		return employee;
	}

	@Override
	public void deleteById(String id) {
		//get current hibernate session
		Session currentSess = entityManager.unwrap(Session.class);
		
		// Using native hibernate api		
		Query<Employee> query = currentSess.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", id);
		
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(Employee employee) {
		//get current hibernate session
		Session currentSess = entityManager.unwrap(Session.class);
		
		//if id = 0 or null, new employee will be created, rule still applies in this case
		currentSess.saveOrUpdate(employee);
	}*/
}
