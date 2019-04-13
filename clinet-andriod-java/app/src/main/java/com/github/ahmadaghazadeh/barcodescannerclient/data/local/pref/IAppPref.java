package com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref;


public interface IAppPref {

    public void saveServerUrl(String url);
    public String loadServerUrl();


}
