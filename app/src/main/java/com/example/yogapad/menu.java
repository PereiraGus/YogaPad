package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    Intent intent_open = getIntent();

    public void viewFunds (View view){
        Intent intent_funds = new Intent(this, funds.class);
        startActivity(intent_funds);
    }

    public void viewPassive (View view){
        Intent intent_passive = new Intent(this, passive.class);
        startActivity(intent_passive);
    }

    public void viewActive (View view){
        Intent intent_active = new Intent(this, active.class);
        startActivity(intent_active);
    }

    public void viewPractice (View view){
        Intent intent_practice = new Intent(this, practice.class);
        startActivity(intent_practice);
    }

    public void backToSplash (View view){
        Intent intent_back = new Intent(this, splash.class);
        startActivity(intent_back);
    }

    public void viewProfile (View view){
        boolean first = false;
        String message;
        if (first == false)
        {
            Intent intent_open = getIntent();
            message = intent_open.getStringExtra("message-key");
        }
        else
        {
            Intent intent_back = getIntent();
            message = intent_back.getStringExtra("message-key");
        }

        Intent intent_profile = new Intent(this, profile.class);
        intent_profile.putExtra("message-key", message);
        startActivity(intent_profile);
    }

    public void viewHelp (View view){
        Intent intent_help = new Intent(this, help.class);
        startActivity(intent_help);
    }

    public void viewHot (View view){
        Intent intent_hot = new Intent(this, hot.class);
        startActivity(intent_hot);
    }

}