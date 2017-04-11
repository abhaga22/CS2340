package com.StrangerPings2340.app;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents waterType
 */

public enum WaterType {

    BOTTLED ("BOTTLED"),
    WELL ("WELL"),
    STREAM ("STREAM"),
    LAKE ("LAKE"),
    SPRING ("SPRING"),
    OTHER ("OTHER");

    private final String waterType;
    /**
     * Creates a user Constructor
     * @param waterType  to store user type
     */
    WaterType(String waterType) {
        this.waterType = waterType;
    }

    /**
     * Changes inputted string to a waterType enum
     * @param level the string level passed in
     * @return WaterType the user level created
     */
    public static WaterType stringToWaterType(String level) {
        if ((level != null)) {
            switch (level) {
                case "BOTTLED":
                    return WaterType.BOTTLED;
                case "WELL":
                    return WaterType.WELL;
                case "STREAM":
                    return WaterType.STREAM;
                case "LAKE":
                    return WaterType.LAKE;
                case "SPRING":
                    return WaterType.SPRING;
                case "OTHER":
                    return WaterType.OTHER;
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
    public static String waterTypeToString(WaterType level) {
        if ((level != null)) {
            switch (level) {
                case BOTTLED:
                    return "BOTTLED";
                case WELL:
                    return "WELL";
                case STREAM:
                    return "STREAM";
                case LAKE:
                    return "LAKE";
                case SPRING:
                    return "SPRING";
                case OTHER:
                    return "OTHER";
                default:
                    return null;
            }
        }
        return null;
    }

    public String toString() {
        return waterType;
    }
}
