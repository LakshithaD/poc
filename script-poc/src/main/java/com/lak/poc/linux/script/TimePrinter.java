// PROJECT : script-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : TimePrinter.java
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
// Date : Oct 19, 2016
// Since : version 1.0
// CLASS : TimePrinter.java
// *************

package com.lak.poc.linux.script;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePrinter {

	public static void main(String[] args) throws InterruptedException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss");
		BufferedWriter writer = null;
		try
		{
		    writer = new BufferedWriter(new FileWriter("time.txt"));
		    while (true) {

		    	writer.write("CURRENT TIME : " + formatter.format(Calendar.getInstance().getTime()));
		    	writer.write("\n");
		    	writer.flush();
		    	Thread.sleep(5000);
			}
		}
		catch ( IOException e)
		{
		}
		finally
		{
		    try
		    {
		        if ( writer != null)
		        writer.close( );
		    }
		    catch ( IOException e)
		    {
		    }
		}
		
	}
}
