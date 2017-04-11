package com.StrangerPings2340.app;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author Rishab
 * @version 1.0
 *
 * Class that represents a Water Purity Report submitted by Workers>=
 */


@SuppressWarnings("unused")
public class WaterPurityReport {

    private long timestamp;
    private String name;
    private LatLng location;
    private String waterCondition;
    private int virusPPM;
    private int contaminantPPM;
    private int reportNumber;
    private String addressString;

    /**
     * Default Constructor
     */
    public WaterPurityReport() {}

    /**
     * Getter method for address as String
     * @return the address as a String
     */
    public String getAddressString() {
        return addressString;
    }
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
     * Getter method for Water Condition
     * @return the type of water Condition
     */
    public String getWaterCondition() {
        return waterCondition;
    }
    /**
     * Setter method for condition
     * @param waterCondition a string containing the new water condition to be set
     */
    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }
    /**
     * Getter method for Water Contamination in PPM
     * @return the type of water contamination in PPM
     */
    public int getContaminantPPM() {
        return contaminantPPM;
    }
    /**
     * Setter method for contamination level
     * @param contaminantPPM an int containing the new water contamination in PPM to be set
     */
    public void setContaminantPPM(int contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
    /**
     * Getter method for Water Virus content in PPM
     * @return the type of water virus content in PPM
     */
    public int getVirusPPM() {
        return virusPPM;
    }
    /**
     * Setter method for virus content level in PPM
     * @param virusPPM an int containing the new virus levels in PPM
     */
    public void setVirusPPM(int virusPPM) {
        this.virusPPM = virusPPM;
    }


    public String toString() {
        return "Report " + Integer.toString(reportNumber) + ":\n\n" + addressString
                + "\n\nWater Condition: " + waterCondition +
                "\n\nVirus PPM: " + Integer.toString(virusPPM)+
                "\n\nContaminant PPM: " + Integer.toString(contaminantPPM);
    }






}
