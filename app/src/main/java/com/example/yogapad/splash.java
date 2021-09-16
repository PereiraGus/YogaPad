package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class splash extends AppCompatActivity {

    EditText textLogin;
    public final static String EXTRA_MESSAGE = ".MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textLogin = (EditText)findViewById(R.id.editTxtLogin);
    }

    public void openApp (View view){
        Intent intent_login = new Intent(this, profile.class);
        String message = textLogin.getText().toString();
        intent_login.putExtra("message-key", message);
        startActivity(intent_login);

        Intent intent_open = new Intent(this, menu.class);
        startActivity(intent_open);
    }

    Intent intent_back = getIntent();

}