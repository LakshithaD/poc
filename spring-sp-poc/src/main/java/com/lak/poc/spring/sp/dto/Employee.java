// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : Employee.java
// ************************************************************************************************
//
// Copyright(C) 2013 Lakshitha
// All rights reserved.
//
// *************************************************************************************************
//
// REVISIONS:
// Author : Lakshitha Matarage
// Date : Oct 14, 2016
// Since : version 1.0
// CLASS : Employee.java
// *************

package com.lak.poc.spring.sp.dto;

import java.math.BigDecimal;

public class Employee {

	private Integer employeeId;

	private String firstName;

	private BigDecimal salary;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

}
