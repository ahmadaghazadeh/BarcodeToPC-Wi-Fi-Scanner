package com.github.ahmadaghazadeh.barcodescannerclient.di;



import com.github.ahmadaghazadeh.barcodescannerclient.ui.connect.ConnectActivity;
import com.github.ahmadaghazadeh.barcodescannerclient.ui.connect.ConnectModule;
import com.github.ahmadaghazadeh.barcodescannerclient.ui.read.ReadActivity;
import com.github.ahmadaghazadeh.barcodescannerclient.ui.read.ReadModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = ReadModule.class)
    abstract ReadActivity bindMainActivity();

    @ContributesAndroidInjector(modules = ConnectModule.class)
    abstract ConnectActivity bindConnectActivity();

}
