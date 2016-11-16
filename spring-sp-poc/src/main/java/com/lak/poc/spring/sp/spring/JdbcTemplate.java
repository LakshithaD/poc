// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : JdbcTemplate.java
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
// CLASS : JdbcTemplate.java
// *************

package com.lak.poc.spring.sp.spring;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;

public class JdbcTemplate extends org.springframework.jdbc.core.JdbcTemplate {

	@Override
	protected Map<String, Object> extractReturnedResults(CallableStatement cs, List updateCountParameters,
			List resultSetParameters, int updateCount) throws SQLException {
		// TODO Auto-generated method stub
		return super.extractReturnedResults(cs, updateCountParameters, resultSetParameters, updateCount);
	}

	@Override
	protected Map<String, Object> extractOutputParameters(CallableStatement cs, List<SqlParameter> parameters)
			throws SQLException {
		// TODO Auto-generated method stub
		Map<String, Object> returnedResults = new HashMap<String, Object>();
		int sqlColIndex = 1;
		for (SqlParameter param : parameters) {
			
			try {
				if (param instanceof SqlOutParameter) {
					SqlOutParameter outParam = (SqlOutParameter) param;
					if (outParam.isReturnTypeSupported()) {
						Object out = outParam.getSqlReturnType().getTypeValue(
								cs, sqlColIndex, outParam.getSqlType(), outParam.getTypeName());
						returnedResults.put(outParam.getName(), out);
					}
					else {
						Object out = cs.getObject(sqlColIndex);
						if (out instanceof ResultSet) {
							if (outParam.isResultSetSupported()) {
								returnedResults.putAll(processResultSet((ResultSet) out, outParam));
							}
							else {
								String rsName = outParam.getName();
								SqlReturnResultSet rsParam = new SqlReturnResultSet(rsName, new ColumnMapRowMapper());
								returnedResults.putAll(processResultSet(cs.getResultSet(), rsParam));
								logger.info("Added default SqlReturnResultSet parameter named " + rsName);
							}
						}
						else {
							returnedResults.put(outParam.getName(), out);
						}
					}
				}
				if (!(param.isResultsParameter())) {
					sqlColIndex++;
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		return returnedResults;
	}

}
