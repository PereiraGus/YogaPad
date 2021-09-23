package com.example.yogapad;

import android.location.Location;


import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationListener;

public class localizacao implements LocationListener {
    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }
}
