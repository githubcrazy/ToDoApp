package com.example.ishanpant.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import model.ApiClientForPost;
import model.ApiInterface;
import model.PostData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextJob;
    private Button postButton;
    private SessionManager sessionManager;
    private PostData post = new PostData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        clickButton();

        sessionManager = new SessionManager(MainActivity.this);

        if(sessionManager.isLoggedIn()) {
            startActivity(new Intent(MainActivity.this, NavDrawerActivity.class));
        }
    }

    private void initializeComponents() {
        editTextName = (EditText) findViewById(R.id.edit_text_name);
        editTextJob = (EditText) findViewById(R.id.edit_text_job);
        postButton = (Button) findViewById(R.id.postButton);
    }

    private void clickButton() {
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.setUser(true,editTextName.getText().toString());
                ApiInterface apiInterface = ApiClientForPost.sendRequest().create(ApiInterface.class);
                Call<PostData> call = apiInterface.postData(editTextName.getText().toString(),editTextJob.getText().toString());
                call.enqueue(new Callback<PostData>() {
                    @Override
                    public void onResponse(Call<PostData> call, Response<PostData> response) {
                        PostData postData = response.body();
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this,NavDrawerActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<PostData> call, Throwable t) {

                    }
                });

            }
        });
    }
}
