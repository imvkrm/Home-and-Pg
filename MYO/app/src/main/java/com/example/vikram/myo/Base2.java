package com.example.vikram.myo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Base2 extends ActionBarActivity {
    EditText etUsername,etPassword;
    String username,password;

    public static final String spMyPREFERENCES="MyPrefs";
    public static final String spName="nameKey";
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorSp));
        }
        sharedPreferences=getSharedPreferences(spMyPREFERENCES,Context.MODE_PRIVATE);
        String spfilevar;
        spfilevar=sharedPreferences.getString(spName,"");
        if (sharedPreferences.contains(spName)){
            Intent i=new Intent(Base2.this,Basedes.class);
            i.putExtra("username",spfilevar);
            startActivity(i);
        }

        if(CheckConnection.isNetworkAvailable(Base2.this)){

        } else {
            Toast.makeText(this, "No Connection Available", Toast.LENGTH_LONG).show();
        }

      //  etUsername=(EditText)findViewById(R.id.etUsername);
        //etPassword=(EditText)findViewById(R.id.etPassword);
    }

    public void buttonSignUp(View v){
        Intent i=new Intent(this,Base1.class);
        startActivity(i);
    }
    public void buttonSkip(View v){
        Intent i=new Intent(this,Basedes.class);
        startActivity(i);
    }

    public void buttonLogIn(View v){
        username=etUsername.getText().toString();
        password=etPassword.getText().toString();
        String type="login";

        if(username.equals("")||password.equals("")){
            Toast.makeText(Base2.this,"PLZ!!Enter Correct Username or Password",Toast.LENGTH_LONG).show();
        }else{
            LogInAsyncTask obj=new LogInAsyncTask(Base2.this);
            obj.execute(type,username,password);
        }
    }

    public class LogInAsyncTask extends AsyncTask<String,Void,String> {
        Context c;
        AlertDialog alertDialog;
        ProgressDialog progressDialog;
        LogInAsyncTask(Context ctx){
            c=ctx;
        }
        @Override
        protected String doInBackground(String... params) {
            String type=params[0];
            String loginURL="http://sandshell.co.nf/login.php";
            if(type.equals("login")){
                try {
                    String user_name=params[1];
                    String psw=params[2];
                    URL url=new URL(loginURL);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                    String post_data= URLEncoder.encode("Username", "UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                            +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(psw,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    //takin input from link
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressDialog=new ProgressDialog(Base2.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Logging in Please wait...");
            progressDialog.setProgress(0);
            progressDialog.show();
            alertDialog=new AlertDialog.Builder(c).create();
            alertDialog.setTitle("Login Status:");
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.cancel();
            if(result==null){
                Toast.makeText(c,"Connection failed",Toast.LENGTH_LONG).show();
            }else if (result.equals("success")){
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(spName,username);
                editor.commit();
                Intent i=new Intent(Base2.this,Basedes.class);
                i.putExtra("username",username);
                startActivity(i);
            }else if(result.equals("failure")){
                alertDialog.setMessage("Username or Password doesn't exist");
                alertDialog.show();
            }else if(result.equals("details missing")){
                alertDialog.setMessage("Server connection failed");
                alertDialog.show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base_n, menu);
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
}
