package com.example.yogapad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Locale;

public class config extends AppCompatActivity {
    DatabaseHelper dbprof;
    users user;
    public static final String BACKUP_PROFILES = "backup_yogapad_users";
    String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        dbprof = new DatabaseHelper(this);
        RadioButton rdtPt = findViewById(R.id.rdbPtConfig);
        RadioButton rbtEng = findViewById(R.id.rdbEngConfig);
        RadioGroup rdgLang;
        String lang;
    }

    /*private static Context changeLanguage(Context context, String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        return context;
    }

    public void changePt(View view)
    {
        lang = "pt-rBR";
        changeLanguage(this, lang);
    }

    public void changeEng(View view)
    {
        lang = "en-rUS";
        changeLanguage(this, lang);
    }*/

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

    public void deleteAll(View view)
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

    public void callBackup(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alertTxtBackup)
                .setPositiveButton(R.string.alertTxtBackupIn, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        backupIn();
                    }
                })
                .setNegativeButton(R.string.alertTxtBackupEx, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        backupEx();
                    }
                });
        AlertDialog alert = builder.create();
        alert.setTitle(R.string.alertTitBackup);
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
            Toast.makeText(getApplicationContext(), "Backup concluído com sucesso.",
                    Toast.LENGTH_SHORT).show();
            return  true;
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Não foi possível criar o backup. Erro: " + e,
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

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

                    File root = android.os.Environment.getExternalStorageDirectory();
                    File dir = new File(root.getAbsolutePath() + "/downloads");
                    //dir.mkdirs();

                    String name= "backup_"+ LocalDateTime.now() + ".json";
                    File archive = new File(dir, name);

                    FileWriter fileWriter = new FileWriter(archive);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(jLista.toString());
                    bufferedWriter.close();
                    Toast.makeText(getApplicationContext(), "Backup criado em: " + archive.getAbsolutePath(),
                            Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Não foi possível criar o backup. Erro: " + e,
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