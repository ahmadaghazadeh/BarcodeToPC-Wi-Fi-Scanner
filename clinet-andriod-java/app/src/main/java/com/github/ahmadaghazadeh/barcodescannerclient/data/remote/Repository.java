package com.github.ahmadaghazadeh.barcodescannerclient.data.remote;

import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.BaseException;

import java.io.IOException;


public class Repository implements IRepository {

    private IApi api;

    public Repository(IApi api) {
        this.api = api;
    }


    @Override
    public ResultData SendQrCodeData(String code) throws IOException, BaseException {
        return api.SendQrCodeData(code);
    }
}
