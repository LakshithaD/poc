// PROJECT : socket-server
// PRODUCT : Affluence CEFT Banking
// CLASS : ServerApplication.java
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
// Date : Dec 19, 2016
// Since : version 1.0
// CLASS : ServerApplication.java
// *************

package com.lak.poc.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServerApplication.class);
	
	private static final Integer SERVER_PORT = 40000;
	
	public static void main(String[] args) {
		
		
		try {
			
			ServerSocket server = new ServerSocket(SERVER_PORT);
			LOGGER.info("ServerSocket started on port : " + SERVER_PORT);
			while (true) {				
				
				LOGGER.info("Waiting for client requests................");
				Socket clinet = server.accept();
				LOGGER.info("Clinet Request accepted PORT : {0}, LOCAL PORT : {1}", clinet.getPort(), clinet.getLocalPort());
				ClientHandler handler = new ClientHandler(clinet);
				Thread t = new Thread(handler);
				t.start();		
				LOGGER.info("Thread started for communication.........	");
			}
			
		} catch (IOException e) {
			
			LOGGER.error("IOException", e);
		}
	}
}
