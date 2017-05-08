package com.example.vikram.myo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Base extends AppCompatActivity {//getstart
    FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorSp));
        }
        MainActivityFragment mainActivityFragment=new MainActivityFragment();

        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        //  fragmentTransaction.replace(R.id.f);
        fragmentTransaction.replace(R.id.fragment_holder,mainActivityFragment );
        fragmentTransaction.commit();

    }
   public void Register(View v){
       Intent i=new Intent(Base.this,Base2.class);
       startActivity(i);
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}/*//for multi adapter
 @Override
    public void onClick(View v) {

        final Intent intent;
        switch (getAdapterPostion()){
            case 0:
               intent =  new Intent(context, FirstActivity.class);
               break;

            case 1:
                intent =  new Intent(context, SecondActivity.class);
                break;
               ...
            default:
               intent =  new Intent(context, DefaultActivity.class);
               break;
         }
        context.startActivity(intent);
    }

*/