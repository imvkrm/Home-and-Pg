package com.example.vikram.myo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

public class Base1 extends AppCompatActivity {//signup page
    String n,u,ps,em,con,o;
    EditText e1,e2,e3,e4,e5;
    String name,email,contact,username,password;
    int load=0;
    int err=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorSp));
        }

       // findViewById(R.id.loadingPane).setVisibility(View.GONE);//progress bar
    }

    public void login(View v){
        Intent i=new Intent(this,Basedes.class);
        startActivity(i);
        finish();
    }

    public void signup(View v){

         e1 = (EditText)findViewById(R.id.t1);
         e2 = (EditText)findViewById(R.id.t2);
         e3 = (EditText)findViewById(R.id.t5);
         e4 = (EditText)findViewById(R.id.t3);
         e5 = (EditText)findViewById(R.id.t4);

        Random r= new Random();
        int otp=(r.nextInt(9998-1099)+1099);
       /* char[] chars ={'5','6','7','8','9'};
        StringBuilder sb = new StringBuilder(); Random random = new Random();
        for (int i = 0; i < 5; i++)
        { char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        o=output;*/

        o=String.valueOf(otp);
        n=e1.getText().toString();
        em=e2.getText().toString();
        con=e3.getText().toString();
        u=e4.getText().toString();
        ps=e5.getText().toString();





        boolean validemail=isValidEmaillId(  em=e2.getText().toString());

        if(n.length()==0){
            e1.setError("PLZ!Enter Your Name");
            err=1;
        }
        if(em.length()==0){
            e2.setError("PLZ!Enter Your Email");
            err=1;
        }else if(validemail!=true){
            e2.setError("PLZ!Enter a Vaild Email Address");
            err=1;
        }
        if(con.length()==0){
            e3.setError("PLZ!Enter Your Mobile No.");
            err=1;
        }
        else if(con.length()!=10){
            e3.setError("Mobile No. must be of 10 digits");
            err=1;
        }
        if(u.length()==0){
            e4.setError("PLZ!Enter any Username ");
            err=1;
        }
        if(ps.length()==0){
            e5.setError("PLZ!Enter any Password ");
            err=1;
        }
        else if(ps.length()>15){
            e5.setError("Password should not be greater 15 digits");
            err=1;
        }


        RegisterAsyn obj=new RegisterAsyn();
        obj.execute();
        load=1;


    }
    private boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    public class RegisterAsyn extends AsyncTask<Void,Void,Void> {
        ProgressDialog progressDialog;
        AlertDialog alertDialog=new AlertDialog.Builder(Base1.this).create();

        @Override
        protected void onPreExecute() {
            if(err==1){
                Toast.makeText(Base1.this,"PLZ!! Fill All the Fields",Toast.LENGTH_SHORT).show();

            }
            else{
           progressDialog=new ProgressDialog(Base1.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Please Wait....");
            progressDialog.setProgress(0);
            progressDialog.show();}

           // findViewById(R.id.loadingPane).setVisibility(View.VISIBLE);
        }




        @Override
        protected Void doInBackground(Void...params){

            reg(Base1.this, n, em,  u, ps);

            return null;}

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            // findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           // progressDialog.cancel();
            name=e1.getText().toString();
            email=e2.getText().toString();
            contact=e5.getText().toString();
            username=e3.getText().toString();
            password=e4.getText().toString();

            if(username.equals("")||password.equals("")||name.equals("")||email.equals("")||contact.equals("")){
                Toast.makeText(Base1.this,"PLZ!! Fill All the Fields",Toast.LENGTH_SHORT).show();}
            else{
            Intent i=new Intent(Base1.this,Basedes.class);
            i.putExtra("user",n);
            i.putExtra("email",em);
            startActivity(i);}}


        }

    static  void reg(Context c, String n, String em,  String u, String ps){

        String serverURL="http://sandshell.co.nf/signup.php";
        Map<String,String> p=new HashMap<String,String>();
        p.put("Name",n);
        p.put("Email",em);
        //p.put("contact", con);
        p.put("Username", u);
        p.put("Password", ps);
       // p.put("otp",o);

        post(serverURL, p);
    }
    public static void post (String severurl,Map<String,String> params)
    {
        URL u =null;
        try {
            u=new URL(severurl);
        }catch (MalformedURLException e){}
        StringBuilder sb=new StringBuilder();
        Iterator<Map.Entry<String,String>> it=params.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String ,String > p=it.next();
            sb.append(p.getKey()).append("=").append(p.getValue());
            if(it.hasNext()){
                sb.append('&');
            }
        }

        String body=sb.toString();
        byte[] bytes=body.getBytes();
        HttpURLConnection uc=null;
        try {

            uc=(HttpURLConnection)u.openConnection();
            uc.setDoOutput(true);
            //uc.setUseCaches(false);
            uc.setFixedLengthStreamingMode(bytes.length);
            uc.setRequestMethod("POST");
            uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            OutputStream out=uc.getOutputStream();

            out.write(bytes);
            out.close();
            int status=uc.getResponseCode();
            if (status!=200)
            {
                Log.d("Invalid request code", "status is " + status);
            }

        }catch(Exception e1)
        {
            Log.d("error", ""+e1.getMessage());
        }

    }

}
