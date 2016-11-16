// PROJECT : http-clinet-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : ClientAuthentication.java
// ************************************************************************************************
//
// Copyright(C) 2013 Fortunaglobal (PRIVATE) LIMITED
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// Fortunaglobal(PRIVATE) LIMITED.
//
// This copy of the Source Code is intended for Fortunaglobal (PRIVATE) LIMITED 's internal use only
// and is
// intended for view by persons duly authorized by the management of Fortunaglobal (PRIVATE)
// LIMITED. No
// part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of Fortunaglobal (PRIVATE) LIMITED.
//
// *************************************************************************************************
//
// REVISIONS:
// Author : Lakshitha Matarage
// Date : Nov 10, 2016
// Since : version 1.0
// CLASS : ClientAuthentication.java
// *************

package com.lak.poc.httpclient;

import java.io.IOException;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientAuthentication {

	public static void main(String[] args) throws IOException {

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope("127.0.0.1", 8080),
				new UsernamePasswordCredentials("tomcatadmin", "tomcat2009"));
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		restart(httpclient);

	}

	private static void restart(CloseableHttpClient httpclient) throws ClientProtocolException, IOException {
		
		try {//http://localhost:8080/manager/text/list
			HttpGet httpget = new HttpGet("http://127.0.0.1:8080/manager/text/reload?path=/transbridge-monitoring-tool-web");

			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				System.out.println(EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}		
	}
	
private static void list(CloseableHttpClient httpclient) throws ClientProtocolException, IOException {
		
		try {//http://localhost:8080/manager/text/list
			HttpGet httpget = new HttpGet("http://127.0.0.1:8080/manager/text/list");

			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				System.out.println(EntityUtils.toString(response.getEntity()));
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}		
	}
}
