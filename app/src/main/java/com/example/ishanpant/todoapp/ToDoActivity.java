package com.example.ishanpant.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.ApiClient;
import model.ApiInterface;
import model.ApplicationManager;
import model.DeletedItemsPojo;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button post;
    private User user = new User();
    private SqliteHelper sqliteHelper;
    private SQLiteDatabase db;
    ApplicationManager applicationManager = new ApplicationManager();
    DisplayDataFragment displayDataFragment = new DisplayDataFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        sqliteHelper = new SqliteHelper(this);
        post = (Button) findViewById(R.id.post_data);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqliteHelper.addActivityToBeDone(editTextName.getText().toString());
                Toast.makeText(ToDoActivity.this,"Data added successfully...",Toast.LENGTH_LONG).show();
            }
        });
    }
}
