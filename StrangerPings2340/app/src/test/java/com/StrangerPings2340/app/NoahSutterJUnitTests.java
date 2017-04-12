package com.StrangerPings2340.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit Tests for M10
 * Created by Noah on 4/11/17.
 *
 * @author Noah Sutter
 * @version 1.0
 */

public class NoahSutterJUnitTests {
    @Test
    public void testWaterTypeToString() throws Exception {
        WaterType exptectedWaterType = WaterType.BOTTLED;
        String testString = "BOTTLED";
        assertEquals("Testing BOTTLED",exptectedWaterType, WaterType.stringToWaterType(testString));

        exptectedWaterType = WaterType.WELL;
        testString = "WELL";
        assertEquals("Testing WELL",exptectedWaterType, WaterType.stringToWaterType(testString));

        exptectedWaterType = WaterType.STREAM;
        testString = "STREAM";
        assertEquals("Testing STREAM",exptectedWaterType, WaterType.stringToWaterType(testString));

        exptectedWaterType = WaterType.LAKE;
        testString = "LAKE";
        assertEquals("Testing LAKE",exptectedWaterType, WaterType.stringToWaterType(testString));

        exptectedWaterType = WaterType.SPRING;
        testString = "SPRING";
        assertEquals("Testing SPRING",exptectedWaterType, WaterType.stringToWaterType(testString));

        exptectedWaterType = WaterType.OTHER;
        testString = "OTHER";
        assertEquals("Testing OTHER",exptectedWaterType, WaterType.stringToWaterType(testString));

        testString = null;
        assertNull("Testing null", WaterType.stringToWaterType(testString));

        testString = "aaabbbccc";
        assertNull("Testing default", WaterType.stringToWaterType(testString));

    }
}
