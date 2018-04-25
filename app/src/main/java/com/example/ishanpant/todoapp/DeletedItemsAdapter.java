package com.example.ishanpant.todoapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import model.DeletedItemsPojo;

public class DeletedItemsAdapter extends RecyclerView.Adapter<DeletedItemsAdapter.ViewHolder> {
    private Cursor cursor;
    private Context context;
    private SQLiteDatabase db;
    private ShowDeletedItems show;

    public DeletedItemsAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_for_deleted_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(!cursor.moveToPosition(position)) {
            return;
        }
        final String data = cursor.getString(cursor.getColumnIndex(ShowDeletedItems.Column_Name));
        holder.deletedItemsText.setText(data);

        show = new ShowDeletedItems(context);
        db = show.getWritableDatabase();
        long id = cursor.getLong(cursor.getColumnIndex(ShowDeletedItems.Column_Id));
        holder.itemView.setTag(id);

//        holder.deletedItemsText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        removeDeletdItems(holder);
//                    }
//                });
//                alertDialog.show();
//            }
//        });
    }

    private void removeDeletdItems(ViewHolder viewHolder) {
        String whereArgs[] = {viewHolder.deletedItemsText.getText().toString()};
        db.delete(ShowDeletedItems.Table_Name, ShowDeletedItems.Column_Name + "=?", whereArgs);
        swapCursorForDeletedItems(show.getDeletedItems());
    }

    public void swapCursorForDeletedItems(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView deletedItemsText;

        public ViewHolder(View itemView) {
            super(itemView);
            deletedItemsText = (TextView) itemView.findViewById(R.id.deleted_items_text_view);
        }
    }
 }
