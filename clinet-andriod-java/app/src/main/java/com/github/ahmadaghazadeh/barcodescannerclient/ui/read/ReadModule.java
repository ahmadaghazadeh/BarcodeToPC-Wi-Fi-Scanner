package com.github.ahmadaghazadeh.barcodescannerclient.ui.read;

import android.arch.lifecycle.ViewModelProvider;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ReadModule {

    @Provides
    ViewModelProvider.Factory getFactory(ReadViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

