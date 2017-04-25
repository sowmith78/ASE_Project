package com.example.application.locus_proj;

/**
 * Created by satheeshchandra on 4/1/17.
 */

public class CoordinatesTracker {
    private String id;
    private double latitude;
    private double longitude;
    public CoordinatesTracker(String id, double latitude, double longitude){
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
