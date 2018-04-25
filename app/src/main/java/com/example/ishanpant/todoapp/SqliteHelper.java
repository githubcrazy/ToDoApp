package com.example.ishanpant.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {
    public static final String DBName = "database.db";
    public static final int DBVersion = 1;

    public static final String USER_TABLE = "remindersTable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "remindActivities";

    public static final String CREATE_TABLE = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT NOT NULL" + ")";

    public SqliteHelper(Context context) {
        super(context, DBName, null, DBVersion);
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

    public Cursor getReminders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(USER_TABLE,null,
                null,
                null,
                null,
                null,COLUMN_ID + " DESC");
    }
}
