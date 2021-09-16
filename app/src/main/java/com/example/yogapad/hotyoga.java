package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class hotyoga extends AppCompatActivity implements SensorEventListener {

    private TextView txtTempHot;
    private SensorManager sensorM;
    private Sensor tempS;
    private Boolean isSensorAvailable;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceStare)
    {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.activity_hotyoga);

        txtTempHot = findViewById(R.id.txtTempHot);
        sensorM = (SensorManager) getSystemService(Context.SEARCH_SERVICE);

        if(sensorM.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
        {
            tempS = sensorM.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isSensorAvailable = true;
        }
        else
        {
            isSensorAvailable = false;
            txtTempHot.setText(null);
        }
    }

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String temperature = event.values[0]+"°C";
        txtTempHot.setText(temperature);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if(isSensorAvailable)
        {
            sensorM.registerListener(this,tempS, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(isSensorAvailable)
        {
            sensorM.unregisterListener(this);
        }
    }
}
