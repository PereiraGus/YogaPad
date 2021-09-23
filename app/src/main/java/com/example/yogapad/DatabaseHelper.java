package com.example.yogapad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbprofiles.db";
    public static final String PROFILE_TABLE_NAME = "tbprofiles";
    public static final String PROFILE_COLUMN_ID = "id";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_LOC = "loc";
    public static final String PROFILE_COLUMN_DESCR = "descr";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table tbprofiles " +
                        "(id integer primary key, name text,loc text,descr text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS tbprofiles");
        onCreate(db);
    }

    public boolean insertContact (users c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_NAME, c.get_name());
        contentValues.put(PROFILE_COLUMN_LOC, c.get_loc());
        contentValues.put(PROFILE_COLUMN_DESCR, c.get_descr());
        db.insert(PROFILE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from tbprofiles where id="+id+"", null );
    }

    public boolean updateUser (users c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_NAME, c.get_name());
        contentValues.put(PROFILE_COLUMN_LOC, c.get_loc());
        contentValues.put(PROFILE_COLUMN_DESCR, c.get_descr());
        db.update(PROFILE_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(c.get_id()) } );
        return true;
    }

    public void deleteAll () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PROFILE_TABLE_NAME,
                null,
                null);
    }

    public ArrayList<String> getAllUsers() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tbprofiles", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(PROFILE_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
    public ArrayList<users> getUsersList() {
        ArrayList<users> list = new ArrayList<>() ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from tbprofiles", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            users pfm = new users();
            pfm.set_id(Integer.parseInt(res.getString(res.getColumnIndex(PROFILE_COLUMN_ID))));
            pfm.set_name(res.getString(res.getColumnIndex(PROFILE_COLUMN_NAME)));
            pfm.set_loc(res.getString(res.getColumnIndex(PROFILE_COLUMN_LOC)));
            pfm.set_descr(res.getString(res.getColumnIndex(PROFILE_COLUMN_DESCR)));

            list.add(pfm);
            res.moveToNext();
        }

        return list;
    }
}
