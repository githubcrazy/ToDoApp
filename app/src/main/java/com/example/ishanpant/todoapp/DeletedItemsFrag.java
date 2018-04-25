package com.example.ishanpant.todoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
    private DeletedItemsAdapter deletedItemsAdapter;

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
         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeDeletedItemForEver((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(deletedItemsRecyclerView);
    }

    private void removeDeletedItemForEver(long id) {
        sqLiteDatabase.delete(ShowDeletedItems.Table_Name,ShowDeletedItems.Column_Id + "=" + id,null);
        deletedItemsAdapter.swapCursorForDeletedItems(showDeletedItems.getDeletedItems());
    }

    private void setUpRecyclerViewForDeletedItems(View view) {
        deletedItemsRecyclerView = view.findViewById(R.id.recyclerViewForDeletedItems);
        LinearLayoutManager linear = new LinearLayoutManager(getActivity());
        deletedItemsRecyclerView.setLayoutManager(linear);
        deletedItemsAdapter = new DeletedItemsAdapter(getActivity(), showDeletedItems.getDeletedItems());
        deletedItemsRecyclerView.setAdapter(deletedItemsAdapter);
    }

}
