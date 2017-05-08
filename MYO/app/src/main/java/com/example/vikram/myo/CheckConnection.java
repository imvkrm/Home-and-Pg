package com.example.vikram.myo;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by vikram on 11/7/2016.
 */
public class CheckConnection {public static boolean isNetworkAvailable(Context c){
    boolean haveConnectedWiFi=false;
    boolean haveConnectedMobile=false;
    try{
        ConnectivityManager cm=(ConnectivityManager)((Activity)c).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo=cm.getAllNetworkInfo();
        for(NetworkInfo ni:netInfo){
            if(ni.getTypeName().equalsIgnoreCase("WIFI"))
                if(ni.isConnected())
                    haveConnectedWiFi=true;
            if(ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if(ni.isConnected())
                    haveConnectedMobile=true;
        }
    }catch (Exception e){

    }
    return haveConnectedMobile||haveConnectedWiFi;
}
}
