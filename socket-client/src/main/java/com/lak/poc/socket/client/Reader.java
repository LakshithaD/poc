// PROJECT : socket-client
// PRODUCT : Affluence CEFT Banking
// CLASS : Reader.java
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
// Date : Jan 5, 2017
// Since : version 1.0
// CLASS : Reader.java
// *************

package com.lak.poc.socket.client;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Reader implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);
	private InputStream in;
		
	public Reader(InputStream in) {
		
		super();
		this.in = in;
	}

	@Override
	public void run() {
		
		while (true) {

			LOGGER.info("Wating for incomming message.............................");
			byte[] read = new byte[200];
			try {
				
				int count = in.read(read);
				LOGGER.info("READ : " + new String(read, 0, count));
			} catch (IOException e) {
				
				LOGGER.error("Reader Exception", e);
				break;
			}
			
		}
	}

	
}
