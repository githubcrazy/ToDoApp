package com.example.ishanpant.todoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import model.GetImages;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<GetImages> getImagesList;

    public RecyclerViewAdapter(Context context , List<GetImages> getImagesList) {
        this.context = context;
        this.getImagesList = getImagesList;
    }

    public void setData(List<GetImages> getImagesList) {
        this.getImagesList = getImagesList;
        notifyDataSetChanged();
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_data_api, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(getImagesList.get(position).getUrl()).into(holder.imageFromApi);
        holder.titleForImage.setText(getImagesList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return getImagesList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageFromApi;
        private TextView titleForImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageFromApi = (ImageView) itemView.findViewById(R.id.image_from_api_response);
            titleForImage = (TextView) itemView.findViewById(R.id.title_for_image);
        }
    }
}
