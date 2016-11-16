// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : GetEmployessSP.java
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
// CLASS : GetEmployessSP.java
// *************

package com.lak.poc.spring.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.lak.poc.spring.sp.mapper.EmployeeMapper;
import com.lak.poc.spring.sp.spring.JdbcTemplate;

import oracle.jdbc.driver.OracleTypes;

public class GetEmployessSP extends StoredProcedure {

	private static final String SPROC_NAME = "get_emp_rs";
	private static final String EMPLOYEE_ID = "p_emp_id";
	private static final String SALARY = "o_salary";
	private static final String CURSOR = "o_cursor";

	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public GetEmployessSP(DataSource dataSource) {
		super();
		setDataSource(dataSource);
		setFunction(false);
		setSql(SPROC_NAME);
		jdbcTemplate.setDataSource(dataSource);
		declareParameter(new SqlParameter(EMPLOYEE_ID, Types.DECIMAL));
		declareParameter(new SqlOutParameter(SALARY, Types.NUMERIC));
		declareParameter(new SqlOutParameter(CURSOR, OracleTypes.CURSOR, new EmployeeMapper()));

		compile();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map execute(Integer empId) {
		Map outs = null;
		try {
			Map inputs = new HashMap();
			System.out.println(empId.intValue());
			inputs.put(EMPLOYEE_ID, empId);
			validateParameters(inputs.values().toArray());
			outs =  jdbcTemplate.call(newCallableStatementCreator(inputs), getDeclaredParameters());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outs;
	}
}
