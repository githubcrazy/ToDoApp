package com.example.ishanpant.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImageDataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ImageDB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "ImageTB";
    public static final String COLUMN_NAME = "RetrieveImages";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME + " BLOB NOT NULL" + ")";
    public ImageDataHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addImage(byte[] RetrieveImages) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,RetrieveImages);
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
    }
}
