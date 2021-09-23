package com.example.yogapad;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class practice extends AppCompatActivity implements FetchAddressTask.OnTaskCompleted{

    //Shared Preferences
    public static final String PREFERENCES_NAME = "com.example.android.localizacao";
    private static final String TRACKING_LOCATION_KEY = "tracking_location";

    //Constantes
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String LASTADDRESS_KEY = "address";
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";

    //classes Location
    private boolean mTrackingLocation;

    //Shared Preferences
    private SharedPreferences mPreferences;
    private String lastLatitude = "";
    private String lastLongitude = "";
    private String lastAddress = "";

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Button mLocationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationBtn = findViewById(R.id.btnFindSchool);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //Recupera o estado da aplicação quando recriado
        if(savedInstanceState != null){
            mTrackingLocation = savedInstanceState.getBoolean(TRACKING_LOCATION_KEY);
        }

        //Inicia os callbacks da location
        mLocationCallback = new LocationCallback() {
            /**
             * This is the callback that is triggered when the
             * FusedLocationClient atualiza a localização.
             * @param locationResult The result containing the device location.
             */
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                // If tracking is turned on, reverse geocode into an address
                if (mTrackingLocation) {
                    new FetchAddressTask(practice.this, practice.this)
                            .execute(locationResult.getLastLocation());
                }
            }
        };

        mLocationBtn.setOnClickListener(new View.OnClickListener(){
            /**
             * Toggle the tracking state.
             * @param v The track location button.
             */
            @Override
            public void onClick(View v) {
                if (!mTrackingLocation) {
                    begin();
                } else {
                    stop();
                }
            }
        });
        //Preferências do usuário
        mPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        recover();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case REQUEST_LOCATION_PERMISSION:
                //Permissão garantida
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    begin();
                }else{
                    //Permissão negada
                    Toast.makeText(this, R.string.location_permission_denied, Toast.LENGTH_SHORT).show();
                }
            break;
        }
    }

    public LocationRequest getLocationRequest(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        return locationRequest;
    }

    private void begin(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }else{
            mTrackingLocation = true;
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(), mLocationCallback, null);
            mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if(location!= null){
                        Geocoder geocoder = new Geocoder(practice.this, Locale.getDefault());
                        try{
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            addresses.get(0).getCountryName();
                            SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, 0);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });

            Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
            search.putExtra(SearchManager.QUERY, "Escolas de Yoga" + lastAddress);
            startActivity(search);
        }
    }

    public void backToMenu(View view) {
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void forward(View view) {
        Intent intent_forw = new Intent(this, hot.class);
        startActivity(intent_forw);
    }

    public void previous(View view) {
        Intent intent_prev = new Intent(this, active.class);
        startActivity(intent_prev);
    }

    public void goToVideo(View view) {

        Uri linkYT = Uri.parse("https://www.youtube.com/watch?v=w9avGO3RQ94");
        Intent intent_YT = new Intent(Intent.ACTION_VIEW, linkYT);
        startActivity(intent_YT);

    }

    private void stop(){
        if(mTrackingLocation){
            mTrackingLocation = false;
            mLocationBtn.setText(R.string.cancel);
        }
    }

    @Override
    public void onTaskCompleted(String[] result){
        if(mTrackingLocation){
            lastAddress = result[0];
            lastLatitude = result[1];
            lastLongitude = result[2];
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        super.onSaveInstanceState(outState);
    }

    private void store(String latitude, String longitude, String lastAddress){
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(LASTADDRESS_KEY, lastAddress);
        preferencesEditor.putString(LATITUDE_KEY, latitude);
        preferencesEditor.putString(LONGITUDE_KEY, longitude);
        preferencesEditor.apply();
    }

    @Override
    protected void onPause(){
        if(mTrackingLocation){
            stop();
            mTrackingLocation = true;
            store(lastLatitude, lastLongitude, lastAddress);
        }
        super.onPause();
    }

    @Override
    protected void onResume(){
        if(mTrackingLocation){
            begin();
        }
        recover();
        super.onResume();
    }

    @SuppressLint("StringFormatMatches")
    private void recover(){
        SharedPreferences mPreferences = getSharedPreferences(PREFERENCES_NAME, 0);
        lastAddress = mPreferences.getString(LASTADDRESS_KEY, "");
        lastLatitude = mPreferences.getString(LATITUDE_KEY, "");
        lastLongitude = mPreferences.getString(LONGITUDE_KEY, "");
    }
}


