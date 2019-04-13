package com.github.ahmadaghazadeh.barcodescannerclient.ui.connect;


import android.arch.lifecycle.MutableLiveData;

import com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref.IAppPref;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.BaseViewModel;

import javax.inject.Inject;


public class ConnectViewModel extends BaseViewModel<IConnectNavigator> {

    public MutableLiveData<String> code=new MutableLiveData<>();
    public MutableLiveData<Boolean> isActiveButton=new MutableLiveData<>();
    @Inject
    public ConnectViewModel() {
        isActiveButton.setValue(false);
    }

    @Inject
    IAppPref appPref;


    public void setCode(String text) {
        isActiveButton.setValue(true);
        code.setValue(text);
    }

    public void connectToServer() {
        appPref.saveServerUrl(code.getValue());
        getNavigator().connectToServer(code.getValue());
    }
}
