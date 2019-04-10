package com.github.ahmadaghazadeh.barcodescannerclient.di;


import android.app.Application;
import android.content.Context;


import com.github.ahmadaghazadeh.barcodescannerclient.app.C;
import com.github.ahmadaghazadeh.barcodescannerclient.data.IRepository;
import com.github.ahmadaghazadeh.barcodescannerclient.data.Repository;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.RetrofitApi;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    @Named("serverUrl")
    public static String getBaseUrl() {
        return C.UrlApi;
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
    public static RetrofitApi provideRetrofitApi(Retrofit retrofit) {
        return new RetrofitApi(retrofit);
    }

    @Provides
    @Singleton
    public static IRepository provideRepository(RetrofitApi api,   Context context) {
        return new Repository(api,   context);
    }
}
