package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class createProfiles extends AppCompatActivity {
    int from_Where_I_Am_Coming = 0;
    private DatabaseHelper dbprof ;

    TextView name ;
    TextView loc;
    TextView descr;
    users users;
    int id_To_Update = 0;

    public void backToSplash (View view){
        Intent intent_back = new Intent(this, splash.class);
        startActivity(intent_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profiles);
        name = findViewById(R.id.txtNameCreate);
        loc = findViewById(R.id.txtLocCreate);
        descr = findViewById(R.id.txtDescrCreate);

        dbprof = new DatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");

            if(Value>0){
                Cursor rs = dbprof.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();
                users = new users();
                users.set_name(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_NAME)));
                users.set_loc(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_LOC)));
                users.set_descr(rs.getString(rs.getColumnIndex(DatabaseHelper.PROFILE_COLUMN_DESCR)));

                if (!rs.isClosed())  {
                    rs.close();
                }
                Button btnCreate = findViewById(R.id.btnCreate);
                btnCreate.setVisibility(View.INVISIBLE);

                name.setText(users.get_name());
                name.setFocusable(true);
                name.setClickable(false);

                loc.setText(users.get_loc());
                loc.setFocusable(false);
                loc.setClickable(false);

                descr.setText(users.get_descr());
                descr.setFocusable(false);
                descr.setClickable(false);
            }
        }
    }

    public void run(View view) {
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            int Value = extras.getInt("id");
            if(Value>0){

                if(dbprof.updateContact(new users( id_To_Update,name.getText().toString(),
                        loc.getText().toString(), descr.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Atualizado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),splash.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "Não Atualizado", Toast.LENGTH_SHORT).show();
                }
            } else{
                if(dbprof.insertContact(new users(name.getText().toString(), loc.getText().toString(),
                        descr.getText().toString()))){
                    Toast.makeText(getApplicationContext(), "Perfil criado",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Não foi possível criar o perfil",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),splash.class);
                startActivity(intent);
            }
        }
    }
}