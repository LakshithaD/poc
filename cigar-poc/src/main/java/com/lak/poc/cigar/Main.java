// PROJECT : cigar-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : Main.java
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
// Date : Oct 27, 2016
// Since : version 1.0
// CLASS : Main.java
// *************

package com.lak.poc.cigar;

import org.hyperic.sigar.ProcCpu;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class Main {

	public static void main(String[] args) {

		try {
			//System.err.println(System.getProperty("java.library.path"));
			final Sigar sigar = new Sigar();
			while (true) {
				
				ProcCpu cpu = sigar.getProcCpu("Exe.Name.ct=eclipse.exe");
				System.out.println(cpu.getPercent());
			}
		} catch (SigarException ex) {
			ex.printStackTrace();
		}
	}
}
