package com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref;


import android.content.Context;


public class AppPref implements IAppPref {

    private static final String IS_INIT_APP = "IS_INIT_APP";
    private static final String USER_XP = "USER_XP";


    private PreferenceManager pm;


    public AppPref(Context context) {
        this.pm = new PreferenceManager(context);
    }


}
