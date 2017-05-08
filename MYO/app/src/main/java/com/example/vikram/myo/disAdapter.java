package com.example.vikram.myo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vikram on 7/15/2016.
 */
public class      disAdapter extends RecyclerView.Adapter<disAdapter.ViewHolder>   {
    private desImp[] data;
    Context c;

    public disAdapter(desImp[] data, Context c) {
        this.data = data;
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // view holder contains detail of view and it is a static class , i contain position
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.descardlayout,parent,false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imageView.setImageResource(data[position].getImageUrl());
       // holder.textView.setText(data[position].getTittle());

    }

    public int getItemCount(){
        return data.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            //textView=(TextView) itemView.findViewById(R.id.desname1);
            imageView=(ImageView) itemView.findViewById(R.id.desphoto);
        }
    }

}

