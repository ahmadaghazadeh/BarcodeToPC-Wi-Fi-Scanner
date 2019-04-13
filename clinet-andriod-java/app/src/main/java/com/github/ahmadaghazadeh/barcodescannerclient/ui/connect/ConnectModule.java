package com.github.ahmadaghazadeh.barcodescannerclient.ui.connect;

import android.arch.lifecycle.ViewModelProvider;

import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class ConnectModule {

    @Provides
    ViewModelProvider.Factory getFactory(ConnectViewModel model){
        return new ViewModelProviderFactory<>(model);
    }

}

