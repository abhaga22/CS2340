package com.StrangerPings2340.app;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * JUnitTests created for M10 by Abhinav
 *
 * @author Abhinav Agarwal
 * @version 1.0
 *
 *
 */

public class AbhinavJUnitTests {
    @Test
    public void testReportCopyOver() {
        WaterSourceReport report1 = null;
        WaterSourceReport report2 = new WaterSourceReport();
        assertFalse("Fails when report to be copied is null",report2.reportCopyOver(report1));
        report1 = new WaterSourceReport();
        report1.setName("Test");
        report1.setAddressString("160 6th street");
        report1.setReportNumber(1);
        report1.setTimestamp(131209);
        report1.setWaterCondition("Potable");
        assertTrue("Report not copied over successfully",report2.reportCopyOver(report1));
        assertEquals("Report could not copy name correctly", report2.getName(),report1.getName());
        assertEquals("Report could not copy address string correctly", report2.getAddressString(),report1.getAddressString());
        assertEquals("Report could not copy report number correctly", report2.getReportNumber(),report1.getReportNumber());
        assertEquals("Report could not copy timestamp correctly", report2.getTimestamp(),report1.getTimestamp());
        assertEquals("Report could not copy water condition correctly", report2.getWaterCondition(),report1.getWaterCondition());

    }
}
