package com.github.ahmadaghazadeh.barcodescannerclient.di;


import android.app.Application;
import android.content.Context;


import com.github.ahmadaghazadeh.barcodescannerclient.data.local.pref.IAppPref;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.Api;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.IApi;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.IRepository;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.Repository;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AppModule {

    @Provides
    @Named("serverUrl")
    public static String getBaseUrl(IAppPref appPref) {
        return appPref.loadServerUrl();
    }

    @Singleton
    @Provides
    public static CommonUtils providesCommonUtils(Context context) {
        return new CommonUtils(context);
    }

    @Binds
    @Singleton
    abstract Context getContext(Application application);


    @Singleton
    @Provides
    public static IApi provideRetrofitApi(Retrofit retrofit, Context context) {
        return new Api(retrofit,context);
    }

    @Provides
    @Singleton
    public static IRepository provideRepository(IApi api, Context context) {
        return new Repository(api);
    }


}
