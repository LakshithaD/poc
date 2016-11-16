// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : EmployeeMapper.java
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
// CLASS : EmployeeMapper.java
// *************

package com.lak.poc.spring.sp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.lak.poc.spring.sp.dto.Employee;

public class EmployeeMapper implements ParameterizedRowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Employee employee = new Employee();
		employee.setEmployeeId(rs.getInt(1));
		employee.setFirstName(rs.getString(2));
		employee.setSalary(rs.getBigDecimal(4));
		return employee;
	}

}
