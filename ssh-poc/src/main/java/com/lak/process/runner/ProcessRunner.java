// PROJECT : ssh-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : ProcessRunner.java
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
// Date : Dec 7, 2016
// Since : version 1.0
// CLASS : ProcessRunner.java
// *************

package com.lak.process.runner;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ProcessRunner {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		 	Process proc = Runtime.getRuntime().exec(" java -jar F:\\LAK\\GIT\\poc\\cigar-poc\\cigar-poc\\cigar-poc-1.0-SNAPSHOT.jar");
		    proc.waitFor();
		    // Then retreive the process output
		    InputStream in = proc.getInputStream();
		    InputStream err = proc.getErrorStream();

		    byte b[]=new byte[in.available()];
		    in.read(b,0,b.length);
		    System.out.println(new String(b));

		    byte c[]=new byte[err.available()];
		    err.read(c,0,c.length);
		    System.out.println(new String(c));
	}
	private void mai() {

		try {

			ProcessBuilder pb = new ProcessBuilder("/path/to/java", "-jar", "your.jar");
			pb.directory(new File("preferred/working/directory"));
			Process p = pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
