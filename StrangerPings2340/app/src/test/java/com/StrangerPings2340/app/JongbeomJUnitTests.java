package com.StrangerPings2340.app;

import org.junit.Test;


import static org.junit.Assert.*;

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
    public void testUserTypeToString() throws Exception {

        UserType test_UserType = UserType.USER;
        String expectedString = "USER";
        assertEquals("User Type Test",expectedString, UserType.userTypeToString(test_UserType));

        test_UserType = UserType.WORKER;
        expectedString = "WORKER";
        assertEquals("Worker Type Test",expectedString, UserType.userTypeToString(test_UserType));

        test_UserType = UserType.MANAGER;
        expectedString = "MANAGER";
        assertEquals("Manager Type Test",expectedString, UserType.userTypeToString(test_UserType));

        test_UserType = UserType.ADMIN;
        expectedString = "ADMIN";
        assertEquals("Admin Type Test",expectedString, UserType.userTypeToString(test_UserType));

        test_UserType = null;
        assertNull("Null Test", UserType.userTypeToString(test_UserType));

        expectedString = "ABC";
        assertNull("Default Test", WaterType.stringToWaterType(expectedString));
    }
}
