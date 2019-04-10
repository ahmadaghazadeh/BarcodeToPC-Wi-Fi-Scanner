package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;


public interface RunnableMethod<TIN, TOUT> {
    TOUT run(TIN param, OnProgressUpdate onProgressUpdate) ;
}

