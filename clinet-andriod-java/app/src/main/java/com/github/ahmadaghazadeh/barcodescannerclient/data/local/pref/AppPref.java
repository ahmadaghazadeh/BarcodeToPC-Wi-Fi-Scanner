package com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref;


import android.content.Context;


public class AppPref implements IAppPref {

    private static final String SERVER_URL = "SERVER_URL";


    private PreferenceManager pm;


    public AppPref(Context context) {
        this.pm = new PreferenceManager(context);
    }


    @Override
    public void saveServerUrl(String url) {
        pm.set(SERVER_URL,url);
    }

    @Override
    public String loadServerUrl() {
     return   pm.get(SERVER_URL,"http://192.168.0.1/");
    }
}
