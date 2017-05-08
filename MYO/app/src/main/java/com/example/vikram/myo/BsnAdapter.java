package com.example.vikram.myo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by vikram on 7/15/2016.
 */
public class BsnAdapter extends RecyclerView.Adapter<BsnAdapter.ViewHolder>   {
    private BsnImp[] data;
   static Context c;

    public BsnAdapter(BsnImp[] data, Context c) {
        this.data = data;
        this.c = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        // view holder contains detail of view and it is a static class , i contain position
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.bsncardlayout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
      //  holder.textView.setTag(position);
       // Log.d("vikram","this is me"+position);
        holder.imageView.setImageResource(data[position].getImageUrl());
        holder.textView.setText(data[position].getTittle());


    }

    public int getItemCount(){
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textView;
        public ImageView imageView;
        ArrayList<BsnImp> bsnImps=new ArrayList<BsnImp>();
        public ViewHolder(View itemView ) {
            super(itemView);
            c = itemView.getContext();
            itemView.setOnClickListener(this);
            textView=(TextView) itemView.findViewById(R.id.name1);
            imageView=(ImageView) itemView.findViewById(R.id.photo);
            itemView.setClickable(true);
            itemView.setFocusable(true);


        }


        @Override
        public void onClick(View v) {
            final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    intent =  new Intent(c, Chandigarh.class);
                   // Toast.makeText(c, " qfd", Toast.LENGTH_SHORT).show();
                    break;

                case 1:
                    intent =  new Intent(c, Delhi.class);
                    break;

                default:
                    intent =  new Intent(c, Gurgaon.class);
                    break;
            }
            v.getContext().startActivity(intent);
        }
    }
    }



