package com.cely404.nomad.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cely404.nomad.R;
import com.cely404.nomad.data.TripData;
import com.cely404.nomad.model.Trip;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class MapActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    
    private GoogleMap mMap = null;
    private GoogleApiClient mLocationClient;
    private static final int REQUEST_LOCATION = 2;
    private static final int ERROR_DIALOG_REQUEST = 4580;

    //to be replaced with a call to DB
    private static final List<Trip> trips = new TripData().getTrips();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOK()) {
            setContentView(R.layout.activity_map);
            Snackbar.make(findViewById(R.id.map), "Services OK, infalting layout", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            initMap();
        }
    }

    /**
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionResult(int requestCode,
                                          String[] permissions,
                                          int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //request was accepted, proceed
                goToCurrentLocation();
            } else {
                //Permission was denied or request was cancelled
                Toast.makeText(this, "Location permissions denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    
    /**
     * Function checks to make sure that the user has google play
     * services available.
     * @return boolean indicating if google play services are available
     */
    public boolean servicesOK() {

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        //Connection was successful, Google Play Services available
        if (resultCode == ConnectionResult.SUCCESS) {
            return true;
        //Connection not successful but user may be able to fix it
        //prompt action
        } else if (googleAPI.isUserResolvableError(resultCode)) {
            Dialog dialog = googleAPI.getErrorDialog(this, resultCode, ERROR_DIALOG_REQUEST);
            dialog.show();
        //Google play services unavailable, cannot proceed
        } else {
            Toast.makeText(getApplicationContext(), "GooglePlayServices not found", Toast.LENGTH_LONG).show();
        }

        return false;
    }


    /**
     *  A google map object is needed to interact with.
     *  This function attemps to initilize the map if it is
     *  null and returns a boolean indicating if it is initialized
     *
     */
    private boolean initMap() {
        //if its null, try to initialize it
        if (mMap == null) {
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    //guaranteed to be non null
                    mMap = googleMap;

                    Snackbar.make(findViewById(R.id.map), "Google Map now initialized", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                    //now that we have a map, try and enable location services
                    mLocationClient = new GoogleApiClient.Builder(MapActivity.this)
                            .addApi(LocationServices.API)
                            .addConnectionCallbacks(MapActivity.this)
                            .addOnConnectionFailedListener(MapActivity.this)
                            .build();
                    mLocationClient.connect();
                }
            });
        }
        return (mMap != null);
    }


    /**
     *  This function will attempt to retrieve the last stored
     */
    public void goToCurrentLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else {
            Location currentLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mLocationClient);
            if (currentLocation == null) {
                Toast.makeText(this, "Couldn't connect!", Toast.LENGTH_LONG).show();
            } else {
                LatLng latLong = new LatLng(
                        currentLocation.getLatitude(),
                        currentLocation.getLongitude()
                );
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
                        latLong, 15
                );
                mMap.animateCamera(update);
            }
        }
    }

    //success callback for location client, mLocationClient
    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Location Services ready!", Toast.LENGTH_LONG).show();
        goToCurrentLocation();
    }

    //conection suspended callback for location client, mLocationClient
    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Location Services suspended", Toast.LENGTH_LONG).show();

    }

    //failed to connect to location services
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Failed to connect to location services!", Toast.LENGTH_LONG).show();

    }
}
