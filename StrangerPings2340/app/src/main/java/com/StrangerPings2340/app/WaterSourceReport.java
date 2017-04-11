package com.StrangerPings2340.app;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents a Water Source Report submitted by all Users
 */


@SuppressWarnings("unused")
public class WaterSourceReport {

    private long timestamp;
    private String name;
    private LatLng location;
    private WaterTypes waterType;
    private String waterCondition;
    private int reportNumber;


    /**
     * Default Constructor
     */
    public WaterSourceReport() {}

    /**
     * Getter method for address as String
     * @return the address as a String
     */
    public String getAddressString() {
        return addressString;
    }
    private String addressString;
    /**
     * Setter method for address
     * @param addressString a String having the address for Water Source Report
     */


    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }
    /**
     * Getter method for report number
     * @return the Report Number
     */
    public int getReportNumber() {
        return reportNumber;
    }
    /**
     * Setter method for report number
     * @param reportNumber an integer having the new Report Number
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }
    /**
     * Getter method for time stamp
     * @return the Time Stamp
     */
    public long getTimestamp() {
        return timestamp;
    }
    /**
     * Setter method for Time Stamp
     * @param timestamp a long integer having the new Report Number
     */

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    /**
     * Getter method for name
     * @return the name of water source report
     */
    public String getName() {
        return name;
    }
    /**
     * Setter method for Water Report
     * @param name a string containing the new Name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter method for Location
     * @return the location of water source report
     */
    public LatLng getLocation() {
        return location;
    }
    /**
     * Setter method for Location
     * @param location a string containing the new location to be added as text
     */
    public void setLocation(LatLng location) {
        this.location = location;
    }
    /**
     * Getter method for Water Type
     * @return the type of water source
     */
    public WaterTypes getWaterType() {
        return waterType;
    }
    /**
     * Setter method for water type
     * @param waterType a string containing the new water type to be set
     */
    public void setWaterType(WaterTypes waterType) {
        this.waterType = waterType;
    }
    /**
     * Getter method for Water Condition
     * @return the type of water condition
     */
    public String getWaterCondition() {
        return waterCondition;
    }
    /**
     * Setter method for Water Condition
     * @param waterCondition the type of water condition to set
     */
    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }


    public String toString() {
        return "Report " + Integer.toString(reportNumber) +
                ":\n\n" + addressString + "\n\nWater Type: " +
                waterType + "\n\nWater Condition: " + waterCondition;
    }






}
