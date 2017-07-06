package com.example.sergey.a2048j;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergey on 16.06.2017.
 */

public class DBRecordWorker extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "game2048";
    public static final String TABLE_NAME = "record";
    private static final String CREATE_TABLE = "create table " + TABLE_NAME
            + " (id integer primary key autoincrement, record integer) ";

    public DBRecordWorker(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL("INSERT INTO " + TABLE_NAME+ " (id, record) VALUES (1, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
