package com.github.ahmadaghazadeh.barcodescannerclient.di;



import com.github.ahmadaghazadeh.barcodescannerclient.ui.MainActivity;
import com.github.ahmadaghazadeh.barcodescannerclient.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();


}
