package com.example.yogapad;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class config extends AppCompatActivity {
    DatabaseHelper dbprof;
    users user;
    public static final String BACKUP_PROFILES = "backup_yogapad_users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        dbprof = new DatabaseHelper(this);
    }

    public void viewHelp(View view)
    {
        Intent intent_help = new Intent(this, help.class);
        startActivity(intent_help);
    }

    public void backToMenu(View view)
    {
        Intent intent_back = new Intent(this, menu.class);
        startActivity(intent_back);
    }

    public void deleteAll()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alertTxtDelete)
                .setPositiveButton(R.string.txtY, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dbprof.deleteAll();
                        Toast.makeText(getApplicationContext(), R.string.tstDeleteY,
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),splash.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.txtN, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                    }
                });

        AlertDialog alert = builder.create();
        alert.setTitle(R.string.alertTitDelete);
        alert.show();
    }

    public boolean backupIn()
    {
        try {
            ArrayList<users> c = dbprof.getUsersList();
            File file = getFileStreamPath(BACKUP_PROFILES);
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            fos.close();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void backupEx()
    {

        String state = Environment.getExternalStorageState();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                try {
                    ArrayList<users> list = dbprof.getUsersList();
                    JSONArray jLista = new JSONArray();

                    for (users c: list
                    ) {
                        jLista.put( c.getJSONObject());
                    }

                    File dir = Environment.getExternalStorageDirectory();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File subDir = new File (dir, "YogaPad");
                    if (!subDir.exists()) {
                        subDir.mkdirs();
                    }
                    String name= "backup_"+ LocalDateTime.now() + ".json";
                    File arquivo = new File(subDir, name);

                    FileWriter fileWriter = new FileWriter(arquivo);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(jLista.toString());
                    bufferedWriter.close();
                    Toast.makeText(getApplicationContext(), arquivo.getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
            else {

                Toast.makeText(getApplicationContext(), "Sem acesso ao armazenamento externo.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}