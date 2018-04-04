package com.example.mapper;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.Locale;

public class DisplayMapActivity extends AppCompatActivity {
    int MAX_RESULTS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
        Intent intent = getIntent();

        Geocoder geocoder;
        List<Address> addresses;

        String location = intent.getStringExtra(MainActivity.EXTRA_LOCATION);
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocationName(location, MAX_RESULTS);
        } catch (Exception e) {
            //TODO: add new intent to display error message to user
            throw new IllegalArgumentException("location Name cannot be null");
        }
    }
}
