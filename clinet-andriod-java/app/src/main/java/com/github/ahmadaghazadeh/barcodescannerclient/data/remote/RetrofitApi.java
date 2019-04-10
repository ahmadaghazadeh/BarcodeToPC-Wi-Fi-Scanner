package com.github.ahmadaghazadeh.barcodescannerclient.data.remote;

import retrofit2.Retrofit;

public class RetrofitApi {

    private Retrofit retrofit;

    public RetrofitApi(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

}
