package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class splash extends AppCompatActivity {

    EditText textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textLogin = (EditText)findViewById(R.id.editTxtLogin);
    }

    public void openApp (View view){
        if(textLogin.getText().toString() == "")
        {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, R.string.txtRequired, Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            String message = textLogin.getText().toString();

            Intent intent_open = new Intent(this, menu.class);
            intent_open.putExtra("message-key", message);
            startActivity(intent_open);
        }
    }

}