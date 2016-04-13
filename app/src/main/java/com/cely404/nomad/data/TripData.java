package com.cely404.nomad.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class TripData {
    private List<Trip> trips = new ArrayList<>();
    public List<Trip> getTrips() {
        return trips;
    }
    public static final String DEPARTING_CITY = "departingCity";
    public static final String DESTINATION_CITY="destinationCity";
    public static final String ORGANIZER_ID = "organizerId";
    public static final String DESCRIPTION = "description";
    public static final String DEPARTURE_DATE = "departureDate";
    public TripData() {
        trips.add(new Trip("San Diego","San Francisco",1, "Trip from SD to SF", "August 27,2016", new LatLng(32.7157,117.1611)));
        trips.add(new Trip("Orange County","San Francisco",1, "Trip from OC to SF", "August 16,2016", new LatLng(33.7175,117.8311)));
        trips.add(new Trip("Orange County","Los Angeles",1, "Trip from OC to LA", "April 26,2016", new LatLng(33.7175,117.8311)));
        trips.add(new Trip("San Diego","Los Angeles",2, "Trip from SD to LA", "June 13,2016", new LatLng(32.7157,117.1611)));
        trips.add(new Trip("San Francisco","San Diego",2, "Trip from SF to SD", "January 1,2017", new LatLng(37.7749,122.4194)));


    }
}
