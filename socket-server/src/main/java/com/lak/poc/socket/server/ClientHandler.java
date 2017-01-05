// PROJECT : socket-server
// PRODUCT : Affluence CEFT Banking
// CLASS : ClientHandler.java
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
// CLASS : ClientHandler.java
// *************

package com.lak.poc.socket.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
	
	Socket client;

	public ClientHandler(Socket client) {

		super();
		this.client = client;
	}

	@Override
	public void run() {

		try {

			InputStream in = client.getInputStream();
			OutputStream out = client.getOutputStream();

			Integer i = 0;
			while (true) {

				byte[] read = new byte[200];
				LOGGER.info("Waiting for bytes...................");
				int count = in.read(read);
				LOGGER.info("READ " + new String(read, 0, count));
				out.write((i.toString() + " : READ").getBytes());
				i++;
			}

		} catch (IOException e) {
			
			LOGGER.error("IOException", e);
		}

	}

}
