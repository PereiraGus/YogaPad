package com.example.yogapad;

import static com.example.yogapad.splash.PREFF_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class profile extends AppCompatActivity {

    TextView txtName;
    TextView txtLoc;
    TextView txtDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtName = findViewById(R.id.txtNameProfile);
        txtLoc = findViewById(R.id.txtLocProfile);
        txtDescr = findViewById(R.id.txtDescrProfile);

        SharedPreferences prefID = getSharedPreferences(PREFF_NAME, 0);
        int sessionID = prefID.getInt("sessionID", 0);

        DatabaseHelper dbusers = new DatabaseHelper(this);

        users user = new users();

        Cursor rs = dbusers.getData(sessionID);
        rs.moveToFirst();

        user.set_name(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_NAME)));
        user.set_loc(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_LOC)));
        user.set_descr(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_DESCR)));

        txtName.setText(user.get_name());
        txtLoc.setText(user.get_loc());
        txtDescr.setText(user.get_descr());
    }

    public void backToMenu (View view){
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }
}