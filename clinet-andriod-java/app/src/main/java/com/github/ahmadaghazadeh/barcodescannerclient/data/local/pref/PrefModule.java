package com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref;


import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefModule {

    @Provides
    @Singleton
    IAppPref getAppPref(Context context) {
        return new AppPref(context);
    }


}
