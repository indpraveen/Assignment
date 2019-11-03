package com.google.firebase.assignment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataSource> mDataset;
    public Context mContex;


   // inner class
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        public ImageView imageView;
        public TextView nameView,ageView,locview;


        public MyViewHolder(View v) {
            super(v);
            view = v;
            imageView= v.findViewById(R.id.image);
           nameView=v.findViewById(R.id.name);
           ageView=v.findViewById(R.id.age);
           locview=v.findViewById(R.id.location);

        }
    }


    public MyAdapter(ArrayList<DataSource> myDataset, Context context) {
       mDataset = myDataset;
        mContex=context;

    }


    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

       View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.imageView.setImageBitmap(mDataset.get(position).getImageSource());
        holder.nameView.setText(mDataset.get(position).getName());
        holder.ageView.setText(mDataset.get(position).getAge());
        holder.locview.setText(mDataset.get(position).getLocation());







    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}