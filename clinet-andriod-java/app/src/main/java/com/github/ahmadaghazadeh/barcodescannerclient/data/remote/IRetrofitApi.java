package com.github.ahmadaghazadeh.barcodescannerclient.data.remote;




import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitApi {

    @POST("api/QrCode/SendQrCodeData")
    Call<ResultData> SendQrCodeData(@Body PostData parameter);

}
