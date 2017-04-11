package com.StrangerPings2340.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit Tests for M10
 * Created by Kelly on 4/10/17.
 *
 * @author Kelly Chen
 * @version 1.0
 */

public class KellyChenJUnitTests {
    @Test
    public void testWaterTypeToString() throws Exception {
        WaterType testWaterType = WaterType.BOTTLED;
        String expectedString = "BOTTLED";
        assertEquals("Testing BOTTLED",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = WaterType.WELL;
        expectedString = "WELL";
        assertEquals("Testing WELL",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = WaterType.STREAM;
        expectedString = "STREAM";
        assertEquals("Testing STREAM",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = WaterType.LAKE;
        expectedString = "LAKE";
        assertEquals("Testing LAKE",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = WaterType.SPRING;
        expectedString = "SPRING";
        assertEquals("Testing SPRING",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = WaterType.OTHER;
        expectedString = "OTHER";
        assertEquals("Testing OTHER",expectedString, WaterType.waterTypeToString(testWaterType));

        testWaterType = null;
        assertNull("Testing null", WaterType.waterTypeToString(testWaterType));

    }
}
