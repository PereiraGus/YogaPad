package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    Intent intent_back = getIntent();

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void goToIG (View view){

        Uri linkIG = Uri.parse("https://www.instagram.com/");
        Intent intent_IG = new Intent(Intent.ACTION_VIEW, linkIG);
        startActivity(intent_IG);

    }

}