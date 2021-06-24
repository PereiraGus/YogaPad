package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class splash extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ".MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void openApp (View view){
        Intent intent_open = new Intent(this, menu.class);
        startActivity(intent_open);
    }

    public void passLogin(View view){
        Intent intent_login = new Intent(this, profile.class);
        EditText textLogin = (EditText) findViewById(R.id.editTxtLogin);
        String message = textLogin.getText().toString();
        intent_login.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent_login);
    }

    Intent intent_back = getIntent();

}