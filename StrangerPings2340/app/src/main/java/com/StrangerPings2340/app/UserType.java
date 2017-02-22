package com.StrangerPings2340.app;

/**
 * Created by Rishab on 2/20/2017.
 */

public enum UserType {
    USER ("User"),
    WORKER ("Worker"),
    MANAGER ("Manager"),
    ADMIN ("Admin");

    private final String userType;

    UserType(String userType) {
        this.userType = userType;
    }

    public String toString() {
        return userType;
    }

}
