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
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        sqliteHelper = new SqliteHelper(this);
        Intent intent = getIntent();
        ArrayList<String> data = intent.getStringArrayListExtra("data");
        editTextName = (EditText) findViewById(R.id.add_a_note_text_view);
        if (data != null) {
            editTextName.setText(String.valueOf(data.get(0)));
        }


        post = (Button) findViewById(R.id.add_data_button);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqliteHelper.addActivityToBeDone(editTextName.getText().toString());
                Toast.makeText(ToDoActivity.this, "Data added successfully...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ToDoActivity.this, NavDrawerActivity.class);
                startActivity(intent);
            }
        });
    }
}
