package com.github.ahmadaghazadeh.barcodescannerclient.utils.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;



public class SharedMainViewModel extends ViewModel {

    public MutableLiveData<SharedMainModel> getModel() {
        return model;
    }

    public void setModel(boolean isIndeterminate, int progress) {
        this.model.postValue(new SharedMainModel(isIndeterminate,progress));
    }

    private MutableLiveData<SharedMainModel> model = new MutableLiveData<>();

}
