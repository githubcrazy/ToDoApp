package com.example.ishanpant.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import model.ApiClient;
import model.ApiInterface;
import model.ApplicationManager;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToDoActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextJob;
    private Button post;
    private User user = new User();
    ApplicationManager applicationManager = new ApplicationManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextJob = (EditText) findViewById(R.id.edit_text_job);
        post = (Button) findViewById(R.id.post_data);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*applicationManager.getDataFromApi(user);*/
                startActivity(new Intent(ToDoActivity.this,MainActivity.class));
            }
        });
    }
}
