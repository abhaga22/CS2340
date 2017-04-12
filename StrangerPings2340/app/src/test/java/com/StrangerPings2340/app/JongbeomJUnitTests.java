package com.StrangerPings2340.app;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * JUnitTests created for M10 by Jongbeom
 *
 * @author Jongbeom Jeon
 * @version 1.0
 *
 *
 */

public class JongbeomJUnitTests {
    @Test
    public void testReportToString() {
        WaterPurityReport purityReport1 = null;
        WaterPurityReport purityReport2 = new WaterPurityReport();
        purityReport1 = new WaterPurityReport();
        purityReport1.setAddressString("1000 Atlantic DR NW, Atlantc, GA 30318");
        purityReport1.setReportNumber(1);
        purityReport1.setTimestamp(100000);
        purityReport1.setName("Water_Purity_Report_Test");
        purityReport1.setLocation(10.50,10.50);
        purityReport1.setWaterCondition("Potable");
        purityReport1.setContaminantPPM(100);
        purityReport1.setVirusPPM(10);

        assertEquals("Report cannot copy name correctly", purityReport2.getName(),purityReport1.getName());
        assertEquals("Report cannot copy address string correctly", purityReport2.getAddressString(),purityReport1.getAddressString());
        assertEquals("Report cannot copy report number correctly", purityReport2.getReportNumber(),purityReport1.getReportNumber());
        assertEquals("Report cannot copy timestamp correctly", purityReport2.getTimestamp(),purityReport1.getTimestamp());
        assertEquals("Report cannot copy water condition correctly", purityReport2.getWaterCondition(),purityReport1.getWaterCondition());
        assertEquals("Report cannot copy water virus PPM correctly", purityReport2.getVirusPPM(),purityReport1.getVirusPPM());
        assertEquals("Report cannot copy water contamination PPM correctly", purityReport2.getContaminantPPM(),purityReport1.getContaminantPPM());
        assertEquals("Report cannot copy location correctly", purityReport2.getLocation(),purityReport1.getLocation());
    }
}
