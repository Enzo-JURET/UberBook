package com.example.uberbook.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "UberBookDatabase";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Books " +"( id INTEGER PRIMARY KEY AUTOINCREMENT, " +"Title TEXT, "+"Description TEXT,  "+"Author TEXT,  "+"status TEXT, "+"Isbn TEXT ) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS Books";
        db.execSQL(sql);
        onCreate(db);
    }
}