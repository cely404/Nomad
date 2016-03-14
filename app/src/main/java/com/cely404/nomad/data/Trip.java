package com.cely404.nomad.data;

import android.os.Bundle;

/**
 * Created by aracely_payan93 on 3/12/16.
 */
public class Trip {

    //constants for field reference
    public static final String DEPARTING_CITY = "departingCity";
    public static final String DESTINATION_CITY="destinationCity";
    public static final String ORGANIZER_ID = "organizerId";
    public static final String DESCRIPTION = "description";
    public static final String DEPARTURE_DATE = "departureDate";

    //private fields
    private String departingCity;
    private String destinationCity;
    private int organizerId;
    private String description;
    private String departureDate;


    public String getDepartingCity() {
        return departingCity;
    }

    //getters and setters
    public void setDepartingCity(String departingCity) {
        this.departingCity = departingCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    //used when creating the data object
    public Trip(String departingCity, String destinationCity, int organizerId, String description, String departureDate){
        this.departingCity = departingCity;
        this.destinationCity = destinationCity;
        this.organizerId = organizerId;
        this.description = description;
        this.departureDate = departureDate;
    }

    //	Create from a bundle
    public Trip(Bundle b) {
        if (b != null) {
            this.departingCity = b.getString(DEPARTING_CITY);
            this.destinationCity = b.getString(DESTINATION_CITY);
            this.organizerId = b.getInt(ORGANIZER_ID);
            this.description = b.getString(DESCRIPTION);
            this.departureDate = b.getString(DEPARTURE_DATE);
        }
    }

    //	Package data for transfer between activities
    public Bundle toBundle() {
        Bundle b = new Bundle();
        b.putString(DEPARTING_CITY, this.departingCity);
        b.putString(DESTINATION_CITY, this.destinationCity);
        b.putInt(ORGANIZER_ID, this.organizerId);
        b.putString(DESCRIPTION, this.description);
        b.putString(DEPARTURE_DATE, this.departureDate);
        return b;
    }

}
