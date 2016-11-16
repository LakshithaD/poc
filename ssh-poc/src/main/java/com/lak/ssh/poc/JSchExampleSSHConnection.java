// PROJECT : ssh-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : JSchExampleSSHConnection.java
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
// Date : Nov 11, 2016
// Since : version 1.0
// CLASS : JSchExampleSSHConnection.java
// *************

package com.lak.ssh.poc;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchExampleSSHConnection {

	/**
	 * JSch Example Tutorial Java SSH Connection Program
	 */
	public static void main(String[] args) {
		String host = "192.168.5.46";
		String user = "root";
		String password = "admin123fg";
		String startcommand = "java -jar /usr/local/TEST/cigar/cigar-poc/cigar-poc-1.0-SNAPSHOT.jar &";
		String pidCommnad = "ps x | grep cigar-poc | grep -v grep | cut -d ' ' -f 2";
		String killCommand = "kill -9 ";

		try {

			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, 22);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			System.out.println("Connected");

			String pid = executeCommnad(pidCommnad, session);
			while (isNumeric(pid)) {

				System.out.println("PID " + pid + " Exitst. Stopping application");
				for (String pida : pid.split("\\n")) {
					
					executeCommnad(killCommand + pida, session);
				}
				pid = executeCommnad(pidCommnad, session);
			}
			System.out.println("Starting the application");
			executeCommnad(startcommand, session);
			session.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String executeCommnad(String pidCommnad, Session session) throws JSchException, IOException {
		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(pidCommnad);
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		channel.connect();
		byte[] tmp = new byte[1024];
		StringBuilder builder = new StringBuilder();
		while (true) {

			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				builder.append(new String(tmp, 0, i));
			}
			if (channel.isClosed()) {

				System.out.println("exit-status: " + channel.getExitStatus());
				break;
			}
			try {

				Thread.sleep(1000);
			} catch (Exception ee) {

			}
		}
		channel.disconnect();
		return builder.toString();
	}

	public static boolean isNumeric(String s) {
		
		if (s != null && s.trim().isEmpty()) {
			
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
