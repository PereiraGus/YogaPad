package com.example.yogapad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class practice extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
    }

    Intent intent_practice = getIntent();

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void forward (View view){
        Intent intent_forw = new Intent(this, menu.class);
        startActivity(intent_forw);
    }

    public void previous (View view){
        Intent intent_prev = new Intent(this, active.class);
        startActivity(intent_prev);
    }

    public void getMap (View view){

        Uri location = Uri.parse("geo:0,0?q=Escolas+de+Yoga");
        Intent intent_map = new Intent(Intent.ACTION_VIEW, location);
        startActivity(intent_map);

    }

}
