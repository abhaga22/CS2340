package com.StrangerPings2340.app;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents waterType
 */

public enum WaterTypes {

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
    WaterTypes(String waterType) {
        this.waterType = waterType;
    }

    /**
     * Changes inputted string to a waterType enum
     * @param level the string level passed in
     * @return WaterType the user level created
     */
    public static WaterTypes stringToWaterType(String level) {
        if ((level != null)) {
            switch (level) {
                case "BOTTLED":
                    return WaterTypes.BOTTLED;
                case "WELL":
                    return WaterTypes.WELL;
                case "STREAM":
                    return WaterTypes.STREAM;
                case "LAKE":
                    return WaterTypes.LAKE;
                case "SPRING":
                    return WaterTypes.SPRING;
                case "OTHER":
                    return WaterTypes.OTHER;
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
    public static String waterTypeToString(WaterTypes level) {
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
            }
        }
        return null;
    }

    public String toString() {
        return waterType;
    }
}
