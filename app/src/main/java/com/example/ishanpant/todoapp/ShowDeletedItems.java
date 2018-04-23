package com.example.ishanpant.todoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ShowDeletedItems extends SQLiteOpenHelper{
    public static final String Database_Name = "deleteditems.db";
    public static final int Database_Version = 4;

    public static final String Table_Name = "ItemsDeleted";
    public static final String Column_Id = "_id";
    public static final String Column_Name = "DeletedItemsName";

    public static final String CREATE_TABLE = "CREATE TABLE " + Table_Name + "("
            + Column_Id + " INTEGER PRIMARY KEY,"
            + Column_Name + " TEXT NOT NULL" + ")";

    public ShowDeletedItems(Context context) {
        super(context, Database_Name, null, Database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE);*/
        /*onCreate(sqLiteDatabase);*/
    }

    public void addDeletedItems(String DeletedItemsName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Name,DeletedItemsName);
        sqLiteDatabase.insert(Table_Name,null,contentValues);
        sqLiteDatabase.close();
    }
}
