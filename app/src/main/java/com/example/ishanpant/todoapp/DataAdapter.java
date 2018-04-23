package com.example.ishanpant.todoapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private Context context;
    private Cursor cursor;
    private SQLiteDatabase db;
    private SqliteHelper sqliteHelper;
    private ShowDeletedItems showDeletedItems;

    public DataAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_notes_and_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (!cursor.moveToPosition(position)) {
            return;
        }
        final String name = cursor.getString(cursor.getColumnIndex(SqliteHelper.COLUMN_NAME));
        long id = cursor.getLong(cursor.getColumnIndex(SqliteHelper.COLUMN_ID));
         holder.activityToBeDone.setText(name);

        sqliteHelper = new SqliteHelper(context);
        db = sqliteHelper.getWritableDatabase();
        holder.activityToBeDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage("Are you sure you want to delete?");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDeletedItems = new ShowDeletedItems(context);
                        showDeletedItems.addDeletedItems(holder.activityToBeDone.getText().toString());
                        Toast.makeText(context, "Deleted Items Added Successfully...", Toast.LENGTH_LONG).show();

                        long id = cursor.getLong(cursor.getColumnIndex(SqliteHelper.COLUMN_ID));
                        holder.activityToBeDone.setTag(id);
                        removeItems(id , holder);

                    }
                });
                alertDialog.show();
            }
        });
    }

    private void removeItems(long id,MyViewHolder viewHolder) {
        viewHolder.activityToBeDone.getTag();
        db.delete(SqliteHelper.USER_TABLE, SqliteHelper.COLUMN_ID + "=" + id, null);
    }

    public void swapCursor(Cursor newCursor) {
        if(cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if(newCursor != null) {
           notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView activityToBeDone;
        private CardView toDoActivityCardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            activityToBeDone = (TextView) itemView.findViewById(R.id.activity_to_be_done_text_view);
            toDoActivityCardView = (CardView) itemView.findViewById(R.id.to_do_activities_card_view);
        }
    }
}
