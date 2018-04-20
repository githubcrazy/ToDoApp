package com.example.ishanpant.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    private static final String DBName = "database.db";
    private static final int DBVersion = 1;

    private static final String USER_TABLE = "remindersTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "remindActivities";

    public static final String CREATE_TABLE = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT NOT NULL" + ")";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBName, factory, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addActivityToBeDone(String remindActivities) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,remindActivities);
        long id = sqLiteDatabase.insert(USER_TABLE,null,values);
        sqLiteDatabase.close();
    }

    public Cursor getActivityInFragment() {
        String remindActivities;
        String selectQuery = "select * from " + USER_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
