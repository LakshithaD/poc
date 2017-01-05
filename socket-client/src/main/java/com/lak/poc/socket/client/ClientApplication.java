// PROJECT : socket-client
// PRODUCT : Affluence CEFT Banking
// CLASS : ClientApplication.java
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
// CLASS : ClientApplication.java
// *************

package com.lak.poc.socket.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientApplication.class);

	public static void main(String[] args) {

		while (true) {

			try {

				LOGGER.info("Connecting to server .............................");
				Socket socket = new Socket("192.168.1.102", 40000);
				LOGGER.info("Connected to server .............................");
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				Scanner sc = new Scanner(System.in);
				while (true) {

					String message = sc.nextLine();
					LOGGER.info("Wating for console message.............................");
					out.write(message.getBytes());
					byte[] read = new byte[200];
					int count = in.read(read);
					LOGGER.info("READ : " + new String(read, 0, count));
				}

			} catch (IOException e) {

				LOGGER.error("IOException", e);
			}
			try {

				Thread.sleep(30000);
			} catch (InterruptedException e) {

				LOGGER.error("InterruptedException", e);
			}
		}
	}
}
