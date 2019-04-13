package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;

public class UncaughtException extends BaseException {
    public UncaughtException(Context context, Exception exception) {
        super();
        this.context=context;
        errorModel.systemMessage = exception.getMessage();
    }
}
