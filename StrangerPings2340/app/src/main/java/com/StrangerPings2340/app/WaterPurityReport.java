package com.StrangerPings2340.app;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Rishab on 2/24/2017.
 */

public class WaterPurityReport {

    private long timestamp;
    private String name;
    private LatLng location;
    private String waterCondition;
    private int virusPPM;
    private int contaminantPPM;
    private int reportNumber;

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    private String addressString;

    public WaterPurityReport() {}

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getWaterCondition() {
        return waterCondition;
    }

    public void setWaterCondition(String waterCondition) {
        this.waterCondition = waterCondition;
    }

    public int getContaminantPPM() {
        return contaminantPPM;
    }

    public void setContaminantPPM(int contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

    public int getVirusPPM() {
        return virusPPM;
    }

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
