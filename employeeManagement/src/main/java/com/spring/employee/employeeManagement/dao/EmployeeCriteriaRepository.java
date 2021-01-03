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
public class EmployeeCriteriaRepository {

	@Autowired
	private EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;
	
	public EmployeeCriteriaRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
	}
	
	public Page<Employee> findAllWithFilters(EmployeePage employeePage, EmployeeSearchCriteria employeeSearchCriteria) {
		CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
		Predicate predicate = getPredicate(employeeSearchCriteria, employeeRoot);
		criteriaQuery.where(predicate);
		setOrder(employeePage, criteriaQuery, employeeRoot);
		
		TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFirstResult(employeePage.getPageNumber() * employeePage.getPageSize());
		typedQuery.setMaxResults(employeePage.getPageSize());
		
		Pageable pageable = getPageable(employeePage);
		
		long employeeCount = getEmployeeCount(predicate);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, employeeCount);
	}

	private Predicate getPredicate(EmployeeSearchCriteria employeeSearchCriteria, Root<Employee> employeeRoot) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(employeeSearchCriteria.getMinSalary())) {
			predicates.add(criteriaBuilder.ge(employeeRoot.get("salary"), 
					employeeSearchCriteria.getMinSalary()));
		}
		if(Objects.nonNull(employeeSearchCriteria.getMaxSalary())) {
			predicates.add(criteriaBuilder.le(employeeRoot.get("salary"), 
					employeeSearchCriteria.getMaxSalary()));
		}
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	private void setOrder(EmployeePage employeePage, CriteriaQuery<Employee> criteriaQuery,
			Root<Employee> employeeRoot) {
		if(employeePage.getSortDirection().equals(Sort.Direction.ASC))
			criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(employeePage.getSortBy())));
		else
			criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(employeePage.getSortBy())));
	}

	private Pageable getPageable(EmployeePage employeePage) {
		Sort sort = Sort.by(employeePage.getSortDirection(), employeePage.getSortBy());
		return PageRequest.of(employeePage.getPageNumber(), employeePage.getPageSize(), sort);
	}

	private long getEmployeeCount(Predicate predicate) {
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<Employee> countRoot = countQuery.from(Employee.class);
		countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
		return entityManager.createQuery(countQuery).getFirstResult();		
	}
}
