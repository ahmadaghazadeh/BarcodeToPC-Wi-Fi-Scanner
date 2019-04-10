package com.github.ahmadaghazadeh.barcodescannerclient.data;

import android.content.Context;

import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.RetrofitApi;


public class Repository implements IRepository {

    private RetrofitApi api;


    private Context context;

    public Repository(RetrofitApi api,   Context context) {
        this.api = api;
        this.context = context;

    }


}
