package com.example.mobilecw_02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelperForSubs extends SQLiteOpenHelper {
    public static final String DATABASE_NAME2 = "mylistofsubslang.db";
    public static final String TABLE_NAME2 = "mylistofsubslang_data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LANGNAME";
    public static final String COL_3 = "LANGCODE";

    private static final String TAG = "DatabaseHelper";

    public DatabaseHelperForSubs(Context context) {
        super(context, DATABASE_NAME2, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME2 + " " +
                "("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_2 + "TEXT,"
                + COL_3 + "TEXT" +
                ");";

        db.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String droptable="DROP TABLE IF EXISTS "+TABLE_NAME2;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL(droptable);
        onCreate(db);
    }

    public boolean insertDataForSubs(String LANGNAME,String LANGCODE) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, LANGNAME);
        contentValues.put(COL_3, LANGCODE);

        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor subsLang(String itemToSubscribe) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME2+" WHERE "+COL_2+" = '"+ itemToSubscribe+"';",null);
        return cursor;
    }

    public Cursor displaySpinner1() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME2+" ORDER BY "+COL_2 +" ASC; ", null);
        return data;
    }

//    public Boolean insertDataForLangCode(String langcode) {
//        Log.d(DATABASE_NAME2, "addData: Adding " + COL_2 + " to " + TABLE_NAME2);
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_3, langcode);
//        long result = db.insert(TABLE_NAME2, null, contentValues);
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }
}
