package com.example.ishanpant.todoapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesAndListFragment extends Fragment {
    private CardView toDoActivitiesCardView;
    private TextView activityToBeDoneTextView;
    private SqliteHelper sqliteHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_and_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        toDoActivitiesCardView = view.findViewById(R.id.to_do_activities_card_view);
        activityToBeDoneTextView = view.findViewById(R.id.activity_to_be_done_text_view);
        /*sqliteHelper = new SqliteHelper(getActivity(),null,null,1);*/
        /*Cursor c = sqliteHelper.getActivityInFragment();
        activityToBeDoneTextView.setText(String.valueOf(c));*/
    }
}
