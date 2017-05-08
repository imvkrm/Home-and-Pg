package com.example.vikram.myo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Base4 extends AppCompatActivity {
    String[] names={"Chandigarh" ,"Gurugram","Delhi"};
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base4);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAc));
        }
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.auto);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,names);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter)      ;}

            public void searc(View v) {
                Intent i =new Intent(Base4.this,Chandigarh.class);
                startActivity(i);
            }
        }




