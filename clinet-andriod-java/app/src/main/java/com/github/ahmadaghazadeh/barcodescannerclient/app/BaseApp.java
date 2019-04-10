package com.github.ahmadaghazadeh.barcodescannerclient.app;

import android.content.Context;
//import android.support.multidex.MultiDex;


import com.github.ahmadaghazadeh.barcodescannerclient.di.AppComponent;
import com.github.ahmadaghazadeh.barcodescannerclient.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApp extends DaggerApplication{
    private Thread.UncaughtExceptionHandler defaultHandler;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this::handleUncaughtException);
        AppComponent component = DaggerAppComponent.builder().getApp(this).build();
        component.inject(this);
        return component;
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        StackTraceElement[] arr = e.getStackTrace();
        final StringBuffer report = new StringBuffer(e.toString());
        final String lineSeperator = "-------------------------------\n\n";
        report.append("--------- Stack trace ---------\n\n");
        for (int i = 0; i < arr.length; i++) {
            report.append("    ");
            report.append(arr[i].toString());
            report.append(lineSeperator);
        }
        int x=0;
        defaultHandler.uncaughtException(thread, e);
    }

}
