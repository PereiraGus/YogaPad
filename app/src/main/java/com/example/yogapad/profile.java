package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView txtNameProfile;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent_profile = getIntent();

        message = intent_profile.getStringExtra("message-key");
        txtNameProfile = (TextView) findViewById(R.id.txtNameProfile);
        txtNameProfile.setText(message);

        Intent intent_username = new Intent();
    }

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        intent_back.putExtra("message-key", message);
        startActivity(intent_back);
    }
}