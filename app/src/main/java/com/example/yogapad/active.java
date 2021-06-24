package com.example.yogapad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class active extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
    }

    Intent intent_active = getIntent();

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void forward (View view){
        Intent intent_forw = new Intent(this, practice.class);
        startActivity(intent_forw);
    }

    public void previous (View view){
        Intent intent_prev = new Intent(this, passive.class);
        startActivity(intent_prev);
    }

}
