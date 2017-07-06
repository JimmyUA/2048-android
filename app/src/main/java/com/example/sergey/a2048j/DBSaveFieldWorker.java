package com.example.sergey.a2048j;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergey on 17.06.2017.
 */

public class DBSaveFieldWorker extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "game2048Fields";
    public static final String TABLE_NAME4 = "saved4";
    public static final String TABLE_NAME5 = "saved5";
    public static final String TABLE_NAME6 = "saved6";
    public static final String TABLE_NAME8 = "saved8";

    public DBSaveFieldWorker(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME4 + " (id integer primary key autoincrement, field text" +
//                ", two integer," +
//                " three integer, four integer, five integer, six integer, seven integer, " +
//                "eight integer, nine integer, ten integer, eleven integer, twelve integer, " +
//                "thirteen integer, fourteen integer, fifteen integer, sixteen integer" +
                ", score integer) ");
        db.execSQL("INSERT INTO " + TABLE_NAME4+ " (id, field, score) VALUES (1, '0', 0)");

        db.execSQL("create table " + TABLE_NAME5 + " (id integer primary key autoincrement, field text" +
//                ", two integer," +
//                " three integer, four integer, five integer, six integer, seven integer, " +
//                "eight integer, nine integer, ten integer, eleven integer, twelve integer, " +
//                "thirteen integer, fourteen integer, fifteen integer, sixteen integer, " +
//                "seventeen integer, eighteen integer, nineteen integer, twenty integer" +
                ", score integer) ");
        db.execSQL("INSERT INTO " + TABLE_NAME5+ " (id, field, score) VALUES (1, '0', 0)");

        db.execSQL("create table " + TABLE_NAME6 + " (id integer primary key autoincrement, field text" +
//                ", two integer," +
//                " three integer, four integer, five integer, six integer, seven integer, " +
//                "eight integer, nine integer, ten integer, eleven integer, twelve integer, " +
//                "thirteen integer, fourteen integer, fifteen integer, sixteen integer, " +
//                "seventeen integer, eighteen integer, nineteen integer, twenty integer, " +
//                "twone integer, twtwo integer," +
//                " twthree integer, twfour integer, twfive integer, twsix integer, twseven integer, " +
//                "tweight integer, twnine integer, tirty integer, trone integer, trtwo integer, " +
//                "trthree integer, trfour integer, trfive integer, trsix integer" +
                ", score integer) ");
        db.execSQL("INSERT INTO " + TABLE_NAME6+ " (id, field, score) VALUES (1, '0', 0)");

        db.execSQL("create table " + TABLE_NAME8 + " (id integer primary key autoincrement, field text" +
//                ", two integer," +
//                " three integer, four integer, five integer, six integer, seven integer, " +
//                "eight integer, nine integer, ten integer, eleven integer, twelve integer, " +
//                "thirteen integer, fourteen integer, fifteen integer, sixteen integer, " +
//                "seventeen integer, eighteen integer, nineteen integer, twenty integer, " +
//                "twone integer, twtwo integer," +
//                " twthree integer, twfour integer, twfive integer, twsix integer, twseven integer, " +
//                "tweight integer, twnine integer, tirty integer, trone integer, trtwo integer, " +
//                "trthree integer, trfour integer, trfive integer, trsix integer, " +
//                "trseven integer, " +
//                "treight integer, trnine integer, fourty integer, " +
//                "frone integer, frtwo integer," +
//                " frthree integer, frfour integer, frfive integer, frsix integer, frseven integer, " +
//                "freight integer, frnine integer, fifty integer, " +
//                "ffone integer, fftwo integer," +
//                " ffthree integer, fffour integer, fffive integer, ffsix integer, ffseven integer, " +
//                "ffeight integer, ffnine integer, sixty integer, sxone integer, sxtwo integer, " +
//                "sxthree integer, sxfour integer" +
                ", score integer) ");
        db.execSQL("INSERT INTO " + TABLE_NAME8+ " (id, field, score) VALUES (1, '0', 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
