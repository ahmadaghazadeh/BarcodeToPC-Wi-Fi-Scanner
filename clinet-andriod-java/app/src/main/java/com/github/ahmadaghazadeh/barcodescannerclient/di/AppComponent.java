package com.github.ahmadaghazadeh.barcodescannerclient.di;

import android.app.Application;


import com.github.ahmadaghazadeh.barcodescannerclient.app.BaseApp;
import com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref.PrefModule;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.ApiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilderModule.class,
        ApiModule.class, PrefModule.class
})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApp app);

    @Override
    void inject(DaggerApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder getApp(Application application);

        AppComponent build();
    }
}
