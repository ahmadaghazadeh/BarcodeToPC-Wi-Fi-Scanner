package com.github.ahmadaghazadeh.barcodescannerclient.ui.read;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity.IActivityNavigator;

public interface IReadNavigator extends IActivityNavigator {

    public void startRead();

    public void connectToServer();
}
