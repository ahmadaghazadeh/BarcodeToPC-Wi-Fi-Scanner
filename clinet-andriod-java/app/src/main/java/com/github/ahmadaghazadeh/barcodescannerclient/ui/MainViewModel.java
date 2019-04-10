package com.github.ahmadaghazadeh.barcodescannerclient.ui;


import android.arch.lifecycle.MutableLiveData;

import com.github.ahmadaghazadeh.barcodescannerclient.data.IRepository;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.BaseViewModel;

import javax.inject.Inject;


public class MainViewModel extends BaseViewModel<IMainNavigator> {

    public MutableLiveData<String> code=new MutableLiveData<>();
    @Inject
    public MainViewModel() {
    }
    //@Inject
    private IRepository repository;


    public void setCode(String text) {
        code.setValue(text);
    }
}
