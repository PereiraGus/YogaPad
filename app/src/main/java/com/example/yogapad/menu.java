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

    public void viewFunds (View view){
        Intent intent_funds = new Intent(this, fundaments.class);
        startActivity(intent_funds);
    }

    public void viewPassivos (View view){
        Intent intent_passivos = new Intent(this, passivos.class);
        startActivity(intent_passivos);
    }

    public void viewAtivos (View view){
        Intent intent_passivos = new Intent(this, ativos.class);
        startActivity(intent_passivos);
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
        Intent intent_profile = new Intent(this, profile.class);
        startActivity(intent_profile);
    }

    public void viewHelp (View view){
        Intent intent_help = new Intent(this, help.class);
        startActivity(intent_help);
    }

}