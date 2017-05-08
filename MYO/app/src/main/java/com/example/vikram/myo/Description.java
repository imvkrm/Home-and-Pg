package com.example.vikram.myo;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        TextView contact=(TextView)findViewById(R.id.contact);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8283074082"));
                startActivity(intent);

            }
        });
        TextView visit=(TextView)findViewById(R.id.email);
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myWebLink = new Intent(Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://mail.google.com/mail/u/0/#inbox"));
                startActivity(myWebLink);

            }
        });

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAc));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.RcyclerView2);
        desImp itemData[]={

                new  desImp (R.drawable.desbed),
                new  desImp (R.drawable.download),
                new  desImp (R.drawable.bath),
                new  desImp (R.drawable.frontview)

        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        disAdapter bsnAdapter=new disAdapter(itemData,this);
        recyclerView.setAdapter(bsnAdapter);





    }

}
