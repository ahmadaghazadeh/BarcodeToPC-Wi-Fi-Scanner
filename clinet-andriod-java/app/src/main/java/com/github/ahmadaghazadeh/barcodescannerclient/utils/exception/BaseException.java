package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;
import android.support.annotation.StringRes;
import android.util.Log;

import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.model.ErrorModel;


public abstract class BaseException extends Exception {

    //@Inject
    Context context;
    protected ErrorModel errorModel;
    public BaseException(String message){
        errorModel = new ErrorModel();
        errorModel.userMessage = message;
    }
    public BaseException() {
        errorModel = new ErrorModel();
    }

    public BaseException(@StringRes  int message){
        errorModel = new ErrorModel();
        errorModel.userMessage = context.getResources().getString(message);
    }

    public final String getUserMessage(){
        return errorModel.userMessage;
    }
    public final String getSystemMessage(){
        return errorModel.systemMessage;
    }
    public final String getUserTitle(){
        return errorModel.userTitle;
    }

    public void log(){
        Log.e(errorModel.userMessage, errorModel.systemMessage);
    }

}
