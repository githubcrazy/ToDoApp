package com.example.ishanpant.todoapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import model.ApiClient;
import model.ApiInterface;
import model.ApplicationManager;
import model.GetImages;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataFromApiFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<GetImages> getImagesList;
    private ApplicationManager applicationManager = new ApplicationManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data_api , container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Notes");
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        getImagesList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(),getImagesList);
        recyclerView.setAdapter(recyclerViewAdapter);
        ApiInterface apiInterface = ApiClient.sendRequest().create(ApiInterface.class);
        Call<List<GetImages>> call = apiInterface.getDataFromApi();
        call.enqueue(new Callback<List<GetImages>>() {
            @Override
            public void onResponse(Call<List<GetImages>> call, Response<List<GetImages>> response) {
                displayProgressDialog();
                getImagesList = response.body();
                recyclerViewAdapter.setData(getImagesList);
            }

            @Override
            public void onFailure(Call<List<GetImages>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    private void displayProgressDialog() {
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setMax(400);
        progress.setMessage("Getting Images...");
        progress.show();
    }
}
