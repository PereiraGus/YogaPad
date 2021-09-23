package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.view.KeyEvent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class splash extends AppCompatActivity {

    DatabaseHelper dbprof;
    private TextView txtEmpty;
    private ListView obj;
    public SharedPreferences prefID;
    public static final String PREFF_NAME = "com.example.yogapad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txtEmpty = findViewById(R.id.txtNoProfSplash);
        obj = findViewById(R.id.listProfSplash);
        dbprof = new DatabaseHelper(this);
        updateList();
    }

    public void createProfile(View view)
    {
        Bundle dataBundle = new Bundle();
        dataBundle.putInt("id", 0);

        Intent intent = new Intent(getApplicationContext(), createProfiles.class);
        intent.putExtras(dataBundle);

        startActivity(intent);
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    public void updateList() {

        ArrayList array_list = dbprof.getAllUsers();

        if (array_list.isEmpty()) {
            txtEmpty.setVisibility(View.VISIBLE);
            obj.setVisibility(View.GONE);

        } else {
            txtEmpty.setVisibility(View.GONE);

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);


            obj.setAdapter(arrayAdapter);
            obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                    int sessionID = arg2 + 1;
                    prefID = getSharedPreferences(PREFF_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefID.edit();
                    editor.putInt("sessionID", sessionID);
                    editor.commit();

                    Intent intent_start = new Intent(getApplicationContext(), menu.class);
                    startActivity(intent_start);
                }
            });
            obj.setVisibility(View.VISIBLE);
        }
    }
}