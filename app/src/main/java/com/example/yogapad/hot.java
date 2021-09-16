package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class hot extends AppCompatActivity implements SensorEventListener {

    private TextView txtTempHot;
    private TextView txtTempDesc;
    private ImageView imgTemp;
    private Sensor temp;
    private Boolean isSensorAvailable;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);

        txtTempHot = findViewById(R.id.txtTempHot);
        txtTempDesc = findViewById(R.id.txtTempDesc);
        imgTemp = findViewById(R.id.imgTemp);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
        {
            temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            isSensorAvailable = true;
        }
        else
        {
            isSensorAvailable = false;
            txtTempHot.setText(null);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String temperature = event.values[0]+"°C";
        int length = String.valueOf(temperature).length();

        if(length == 6)
        {
            txtTempHot.setTextSize(View.AUTOFILL_TYPE_TEXT, 70);
        }
        if(length == 7) {
            txtTempHot.setTextSize(View.AUTOFILL_TYPE_TEXT, 55);
        }
        if(length == 8)
        {
            txtTempHot.setTextSize(View.AUTOFILL_TYPE_TEXT, 50);
        }
        if(length == 9)
        {
            txtTempHot.setTextSize(View.AUTOFILL_TYPE_TEXT, 45);
        }
        if(length == 10)
        {
            txtTempHot.setTextSize(View.AUTOFILL_TYPE_TEXT, 40);
        }
        txtTempHot.setText(temperature);
        if(event.values[0] < -20)
        {
            txtTempDesc.setText(R.string.txtHotDesc1);
            imgTemp.setImageResource(R.drawable.radioactive);
        }
        if(event.values[0] > -20 && event.values[0] < 20)
        {
            txtTempDesc.setText(R.string.txtHotDesc2);
            imgTemp.setImageResource(R.drawable.ice);
        }
        if(event.values[0] > 20 && event.values[0] < 40)
        {
            txtTempDesc.setText(R.string.txtHotDesc3);
            imgTemp.setImageResource(R.drawable.yogamat);
        }
        if(event.values[0] > 40 && event.values[0] < 55)
        {
            txtTempDesc.setText(R.string.txtHotDesc4);
            imgTemp.setImageResource(R.drawable.steam);
        }
        if(event.values[0] > 55)
        {
            txtTempDesc.setText(R.string.txtHotDesc5);
            imgTemp.setImageResource(R.drawable.fire);
        }
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
            sensorManager.registerListener(this,temp, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        if(isSensorAvailable)
        {
            sensorManager.unregisterListener(this);
        }
    }

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void forward (View view){
        Intent intent_forw = new Intent(this, menu.class);
        startActivity(intent_forw);
    }

    public void previous (View view){
        Intent intent_prev = new Intent(this, practice.class);
        startActivity(intent_prev);
    }
}