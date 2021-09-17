package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

/*import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;*/

public class map extends AppCompatActivity implements findLoc.OnTaskCompleted {

    public static final String PREFERENCES_NAME = "com.example.yogapad";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    private static final String LASTADRESS_KEY = "adress";

    private Button mLocationButton;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;

    private boolean mTrackingLocation;

    private SharedPreferences mPreferences;
    private String lastLatitude = "";
    private String lastLongitude = "";
    private String lastAdress = "";

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        mLocationButton = findViewById(R.id.btnMapPract);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(savedInstanceState != null){
            mTrackingLocation = savedInstanceState.getBoolean(TRACKING_LOCATION_KEY);
        }

        mLocationButton.setOnClickListener(v -> {
            if(!mTrackingLocation){
                startTrackingLocation();
            } else{
                stopTrackingLocation();
            }
        });

        mLocationCallback = new LocationCallback(){
            @Override public void onLocationResult(LocationResult locationResult){
                if(mTrackingLocation){
                    new findLoc(map.this, map.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };

    }

    private void startTrackingLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACESS_COARSE_LOCATION},REQUEST_LOCATION_PERMISSION);
        } else{
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
        }
    }



}
