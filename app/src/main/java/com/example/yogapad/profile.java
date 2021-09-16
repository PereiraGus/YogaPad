package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView txtNameProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent_profile = getIntent();

        String message = intent_profile.getStringExtra("message-key");
        txtNameProfile = (TextView)findViewById(R.id.txtNameProfile);
        txtNameProfile.setText(message);
    }

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }
}