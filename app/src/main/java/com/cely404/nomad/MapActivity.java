package com.cely404.nomad;

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

public class MapActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private GoogleMap mMap = null;
    private GoogleApiClient mLocationClient;
    private static final int REQUEST_LOCATION = 2;
    private static final int ERROR_DIALOG_REQUEST = 4580;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOK()) {
            setContentView(R.layout.activity_map);
            Snackbar.make(findViewById(R.id.map), "Services OK", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            initMap();
        }
    }


    public void onRequestPermissionResult(int requestCode,
                                          String[] permissions,
                                          int[] grantResults) {
        Toast.makeText(this, "Entering onRequestPermssionResult", Toast.LENGTH_LONG).show();

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

    public boolean servicesOK() {
        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
        int resultCode = googleAPI.isGooglePlayServicesAvailable(this);
        if (resultCode == ConnectionResult.SUCCESS) {
            return true;
        } else if (googleAPI.isUserResolvableError(resultCode)) {
            Dialog dialog = googleAPI.getErrorDialog(this, resultCode, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getApplicationContext(), "GooglePlayServices not found", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            Snackbar.make(findViewById(R.id.map), "map is null, in initmap", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    Snackbar.make(findViewById(R.id.map), "Google Map init now", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
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
//                Locale locale = new Locale(Locale.ENGLISH.getLanguage());
//                Address address = new Address(locale);
//                address.set
            }
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "OnConnected ready!", Toast.LENGTH_LONG).show();
        goToCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "OnConnected Suspended!", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "OnConnected Failed!", Toast.LENGTH_LONG).show();

    }
}
