// PROJECT : spring-sp-poc
// PRODUCT : POC
// CLASS : ProcExecuteService.java
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
// CLASS : ProcExecuteService.java
// *************

package com.lak.poc.spring.sp;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProcExecuteService {

	private GetSalaryProcedure getSalProc;

	private GetEmployessSP getEmployessSP;

	public void getSalary() {
		Map outs = getSalProc.execute(100);
		java.math.BigDecimal sal = (java.math.BigDecimal) outs.get("o_salary");
		System.out.println(sal.intValue());
	}

	public void getEmployessSP() {
		Map outs = getEmployessSP.execute(101);
		//List<Employee> sal = (List<Employee>) outs.get("o_salary");
		java.math.BigDecimal sal = (java.math.BigDecimal) outs.get("o_salary");
		System.out.println(sal.intValue());
	}

	public static void main(String args[]) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ProcExecuteService service = (ProcExecuteService) ctx.getBean("procExecuteService");
		// service.getSalary();
		service.getEmployessSP();
	}

	public GetSalaryProcedure getGetSalProc() {
		return getSalProc;
	}

	public void setGetSalProc(GetSalaryProcedure getSalProc) {
		this.getSalProc = getSalProc;
	}

	public GetEmployessSP getGetEmployessSP() {
		return getEmployessSP;
	}

	public void setGetEmployessSP(GetEmployessSP getEmployessSP) {
		this.getEmployessSP = getEmployessSP;
	}

}
