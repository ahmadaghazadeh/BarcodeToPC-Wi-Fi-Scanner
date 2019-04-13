package com.github.ahmadaghazadeh.barcodescannerclient.data.remote;




import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.PostData;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.ResultData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitApi {

    @POST("api/QrCode/SendQrCodeData")
    Call<ResultData> SendQrCodeData(@Body PostData parameter);

}
