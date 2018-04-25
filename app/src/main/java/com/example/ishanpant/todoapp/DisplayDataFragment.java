package com.example.ishanpant.todoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class DisplayDataFragment extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private FloatingActionButton fab;
    private SQLiteDatabase db;
    private SqliteHelper sql;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_data, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sql = new SqliteHelper(getActivity());
        db = sql.getReadableDatabase();
        setUpRecyclerView(view);
        clickFloatingActionButton(view);
    }

    private void clickFloatingActionButton(View view) {
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ToDoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewActivity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        dataAdapter = new DataAdapter(getActivity() , sql.getReminders());
        recyclerView.setAdapter(dataAdapter);
    }

}
