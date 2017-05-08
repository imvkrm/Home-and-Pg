package com.example.vikram.myo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Chandigarh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chandigarh);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAc));
        }
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.RcyclerView1);
        ChdImp itemData[]={

                new  ChdImp ("Sector 22A","Rs 5000pm",R.drawable.desbed),
                new  ChdImp ("Sector 17A","Rs 4500pm",R.drawable.chd2),
                new  ChdImp ("Sector 2C","Rs 6000pm",R.drawable.chd),
                new  ChdImp ("Sector 12","Rs 5500pm",R.drawable.hspm),
               /* new  BsnImp("mypic5",R.drawable.pic5),
                new  BsnImp("mypic6",R.drawable.pic6),
                new  BsnImp("mypic7",R.drawable.pic7),*/

        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subAdapter bsnAdapter=new subAdapter(itemData,this);
        recyclerView.setAdapter(bsnAdapter);


    }
    public  void des(View v){
        Intent i=new Intent(this,Description.class);
        startActivity(i);
    }

}
