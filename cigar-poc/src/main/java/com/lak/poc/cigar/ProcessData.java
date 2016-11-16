// PROJECT : cigar-poc
// PRODUCT : Affluence Connect Transbridge
// CLASS : ProcessData.java
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
// CLASS : ProcessData.java
// *************

package com.lak.poc.cigar;

import java.util.Map;

import org.hyperic.sigar.ProcStat;
import org.hyperic.sigar.ProcState;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class ProcessData {

	private static Sigar sigar;

    public ProcessData(Sigar s) throws SigarException {
        this.sigar = s;
        System.out.println(getMetric().toString());
        System.out.println(getMetric(getPidString()).toString());
    }

    public static void main(String[] args) throws SigarException {
        new ProcessData(new Sigar());
        System.out.println(ProcessData.getMetric());
        System.out.println(ProcessData.getMetric(getPidString()));
    }

    public static Map<String, String> getMetric() throws SigarException {
        ProcStat state = sigar.getProcStat();
        return (Map<String, String>) state.toMap();
    }

    public static Map<String, String> getMetric(String pid) throws SigarException {
        ProcState state = sigar.getProcState(pid);
        return (Map<String, String>) state.toMap();
    }

    public static long getPid() {
        return sigar.getPid();
    }

    public static String getPidString() {
        return ""+sigar.getPid();
    }

}
