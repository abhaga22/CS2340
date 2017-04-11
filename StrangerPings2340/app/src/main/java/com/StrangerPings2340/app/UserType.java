package com.StrangerPings2340.app;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents userType of User
 */

public enum UserType {
    USER ("USER"),
    WORKER ("WORKER"),
    MANAGER ("MANAGER"),
    ADMIN ("ADMIN");

    private final String userType;
    /**
     * Creates a user Constructor
     * @param userType  to store user type
     */
    UserType(String userType) {
        this.userType = userType;
    }

    /**
     * Changes inputted string to a userType enum
     * @param level the string level passed in
     * @return UserLevel the user level created
     */
    @SuppressWarnings("unused")
    public static UserType stringToUserType(String level) {
        if ((level != null)) {
            switch (level) {
                case "USER":
                    return UserType.USER;
                case "WORKER":
                    return UserType.WORKER;
                case "MANAGER":
                    return UserType.MANAGER;
                case "ADMIN":
                    return UserType.ADMIN;
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Changes inputted userType to a string
     * @param level the string level passed in
     * @return String the user level string
     */
    @SuppressWarnings("unused")
    public static String userTypeToString(UserType level) {
        switch (level) {
            case USER:
                return "USER";
            case WORKER:
                return "WORKER";
            case MANAGER:
                return "MANAGER";
            case ADMIN:
                return "ADMIN";
            default:
                return null;
        }
    }


    public String toString() {
        return userType;
    }

}
