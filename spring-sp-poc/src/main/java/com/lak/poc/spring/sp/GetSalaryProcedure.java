// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : GetSalaryProcedure.java
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
// CLASS : GetSalaryProcedure.java
// *************

package com.lak.poc.spring.sp;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.lak.poc.spring.sp.spring.JdbcTemplate;

public class GetSalaryProcedure extends StoredProcedure {

	private static final String SPROC_NAME = "get_salary";
	private static final String EMPLOYEE_ID = "p_emp_id";
	private static final String SALARY = "o_salary";
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public GetSalaryProcedure(DataSource dataSource) {
		super();
		setDataSource(dataSource);
		setFunction(false);
		setSql(SPROC_NAME);

		declareParameter(new SqlParameter(EMPLOYEE_ID, Types.DECIMAL));
		declareParameter(new SqlOutParameter(SALARY, Types.NUMERIC));

		compile();
	}

	public Map execute(Integer empId) {
		Map outs = null;
		try {
			Map inputs = new HashMap();
			System.out.println(empId.intValue());
			inputs.put(EMPLOYEE_ID, empId);
			//outs = super.execute(inputs);
			validateParameters(inputs.values().toArray());
			return jdbcTemplate.call(newCallableStatementCreator(inputs), getDeclaredParameters());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outs;
	}
}
