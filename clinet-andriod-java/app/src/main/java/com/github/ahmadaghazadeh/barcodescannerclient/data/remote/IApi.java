package com.github.ahmadaghazadeh.barcodescannerclient.data.remote;

import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.ResultData;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.BaseException;

import java.io.IOException;


public interface IApi {
    public ResultData SendQrCodeData(String code) throws BaseException, IOException;
}
