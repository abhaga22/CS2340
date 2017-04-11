package com.StrangerPings2340.app;

import org.junit.Test;


import static org.junit.Assert.*;

/**
 * JUnitTests created for M10 by Rishab Kaup
 *
 * @author Rishab Kaup
 * @version 1.0
 *
 *
 */
public class RishabKaupJUnitTests {
    @Test
    public void testUserTypeToString() throws Exception {
        UserType testUserType = UserType.USER;
        String expectedString = "USER";
        assertEquals("Testing USER",expectedString, UserType.userTypeToString(testUserType));

        testUserType = UserType.WORKER;
        expectedString = "WORKER";

        assertEquals("Testing WORKER",expectedString, UserType.userTypeToString(testUserType));

        testUserType = UserType.MANAGER;
        expectedString = "MANAGER";

        assertEquals("Testing MANAGER",expectedString, UserType.userTypeToString(testUserType));

        testUserType = UserType.ADMIN;
        expectedString = "ADMIN";

        assertEquals("Testing ADMIN",expectedString, UserType.userTypeToString(testUserType));

        testUserType = null;

        assertNull("Testing null", UserType.userTypeToString(testUserType));
    }
}
