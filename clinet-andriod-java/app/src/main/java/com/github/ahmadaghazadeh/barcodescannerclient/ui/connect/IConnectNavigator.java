package com.github.ahmadaghazadeh.barcodescannerclient.ui.connect;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity.IActivityNavigator;

public interface IConnectNavigator extends IActivityNavigator {

    public void connectToServer(String url);
}
