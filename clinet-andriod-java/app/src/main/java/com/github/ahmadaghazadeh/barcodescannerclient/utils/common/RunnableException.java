package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

public interface RunnableException<TIN> {
    void run(TIN param, Exception ex);
}

