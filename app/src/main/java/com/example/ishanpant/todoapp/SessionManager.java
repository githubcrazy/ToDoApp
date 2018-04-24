package com.example.ishanpant.todoapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import model.PostData;

public class SessionManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    PostData postData = new PostData();
    private String id;
    public static final String KEY_NAME = "name";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("MyApp",Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setUser(boolean isLoggedIn, String name) {
        editor.putBoolean("loggedIn", isLoggedIn);
        editor.putString("loggedIn",postData.getId());
        editor.commit();
    }

    public HashMap<String,String> getUserDetails() {
        HashMap<String,String> user = new HashMap<String, String>();
        user.put(KEY_NAME,preferences.getString(KEY_NAME,null));
        return user;
    }

    public boolean isLoggedIn() {
        return preferences.getBoolean("loggedIn",false);
    }
}
