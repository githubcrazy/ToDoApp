package com.example.ishanpant.todoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import model.DeletedItemsPojo;

public class DeletedItemsFrag extends Fragment {
    private RecyclerView deletedItemsRecyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private ShowDeletedItems showDeletedItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.deleted_items_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        showDeletedItems = new ShowDeletedItems(getActivity());
        sqLiteDatabase = showDeletedItems.getReadableDatabase();
        setUpRecyclerViewForDeletedItems(view);
    }

    private void setUpRecyclerViewForDeletedItems(View view) {
        deletedItemsRecyclerView = view.findViewById(R.id.recyclerViewForDeletedItems);
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        deletedItemsRecyclerView.setLayoutManager(linear);
        DeletedItemsAdapter deletedItemsAdapter = new DeletedItemsAdapter(getActivity(), getDeletedItems());
        deletedItemsRecyclerView.setAdapter(deletedItemsAdapter);
    }

    private Cursor getDeletedItems() {
       return sqLiteDatabase.query(showDeletedItems.Table_Name,
               null,
               null,
               null,
               null,
               null, showDeletedItems.Column_Name + " DESC");
    }
}
