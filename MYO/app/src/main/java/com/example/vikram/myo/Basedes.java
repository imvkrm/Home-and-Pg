package com.example.vikram.myo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class Basedes extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG_FEEDBACK = "fedbck";
    public static final String TAG_SHARE = "share";
    public static final String TAG_LOGOUT = "logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basedes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAc));
        }

        String x, y;
        x = getIntent().getStringExtra("user");
        y = getIntent().getStringExtra("email");
        TextView t1 = (TextView) findViewById(R.id.uname);
        if (x == null) {
            x = "Sir";
        }
        t1.setText("Hello " + x);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RcyclerView);
        BsnImp itemData[] = {

                new BsnImp("Chandigarh", R.drawable.chdm),
                new BsnImp("Delhi", R.drawable.delm),
                new BsnImp("Gurgaon", R.drawable.gurm),


        };

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        BsnAdapter bsnAdapter = new BsnAdapter(itemData, this);
        recyclerView.setAdapter(bsnAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.subfab));
        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.lockout);
        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.ic_menu_share);
        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.feedback);

        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();

        button3.setTag(TAG_FEEDBACK);
        button2.setTag(TAG_SHARE);
        button1.setTag(TAG_LOGOUT);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)

                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .attachTo(fab)
                .build();


    }
   /* public void popup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.base_n, popup.getMenu());
        popup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.base_n, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.Profile)
        {
            Intent i=new Intent(this,Base3.class);
            startActivity(i);
        }
        if (id == R.id.Booking)
        {
            Intent i=new Intent(this,Base4.class);
            startActivity(i);
        }
        if (id == R.id.About)
        {
            Intent i=new Intent(this,Base4.class);
            startActivity(i);
        }

        return true;
    }*/


    public void search(View v) {
        Intent i = new Intent(this, Base4.class);
        startActivity(i);

    }

    public void loc(View v) {
        Intent i = new Intent(this, Map1.class);
        startActivity(i);
    }
 /* public void detail(View v){
    Intent i=new Intent(this,Chandigarh.class);
     startActivity(i);
}*/



    @Override
    public void onClick(View v) {
        if(v.getTag().equals(TAG_FEEDBACK)){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"vkrmsingh8697@gmail.com"});

            try {
                startActivity(Intent.createChooser(i, "Send Feedback..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(Basedes.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }


        }
        if(v.getTag().equals(TAG_SHARE)){
            String shareBody = "HEY , download this app to know pg nearby";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent,"Invite Friends"));
           // startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));

        }
        if(v.getTag().equals(TAG_LOGOUT)){
            Intent i=new Intent(Basedes.this,Base2.class);
            startActivity(i);


        }
    }
}
