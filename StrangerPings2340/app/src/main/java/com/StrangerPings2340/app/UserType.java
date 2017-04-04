package com.StrangerPings2340.app;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents userType of User
 */

public enum UserType {
    USER ("User"),
    WORKER ("Worker"),
    MANAGER ("Manager"),
    ADMIN ("Admin");

    private final String userType;
    /**
     * Creates a user Constructor
     * @param userType  to store user type
     */
    UserType(String userType) {
        this.userType = userType;
    }

    public String toString() {
        return userType;
    }

}
