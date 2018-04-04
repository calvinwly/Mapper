package com.example.mapper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    int MAX_RESULTS = 5;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent intent = getIntent();

        Geocoder geocoder;
        List<Address> addresses;

        String location = intent.getStringExtra(MainActivity.EXTRA_LOCATION);
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocationName(location, MAX_RESULTS);
            if (addresses.size() == 0) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

                dlgAlert.setMessage("No results!");
                dlgAlert.setTitle("Error");
                dlgAlert.setCancelable(false);
                dlgAlert.setPositiveButton("Return to Search",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                dlgAlert.create().show();
                return;
            }
            Address main = addresses.get(0);
            LatLng main_loc = new LatLng(main.getLatitude(), main.getLongitude());
            for (Address a : addresses) {
                LatLng loc = new LatLng(a.getLatitude(), a.getLongitude());
                mMap.addMarker(new MarkerOptions().position(loc).title(a.getAddressLine(0)));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(main_loc));
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: add new intent to display error message to user
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("Something went wrong!");
            dlgAlert.setTitle("Error");
            dlgAlert.setCancelable(false);
            dlgAlert.setPositiveButton("Return to Search",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            dlgAlert.create().show();
            return;
        }
    }
}
