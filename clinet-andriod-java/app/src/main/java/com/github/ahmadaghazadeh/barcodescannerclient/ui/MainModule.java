package com.github.ahmadaghazadeh.barcodescannerclient.ui;

import android.arch.lifecycle.ViewModelProvider;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    ViewModelProvider.Factory getFactory(MainViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

