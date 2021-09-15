package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent_login = getIntent();
        String message = intent_login.getStringExtra(splash.EXTRA_MESSAGE);

        TextView txtNameProfile = findViewById(R.id.txtNameProfile);
        txtNameProfile.setText(message);
    }

    Intent intent_profile = getIntent();

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }
}