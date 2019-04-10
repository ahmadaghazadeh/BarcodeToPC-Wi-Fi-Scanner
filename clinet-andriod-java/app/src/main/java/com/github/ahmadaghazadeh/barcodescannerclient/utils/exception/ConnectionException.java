package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;

import com.github.ahmadaghazadeh.barcodescannerclient.R;


public class ConnectionException extends BaseException {

    //@Inject
    public Context context;

    public ConnectionException(Exception exception) {
        super();
        errorModel.systemMessage = exception.getMessage();
        generateMessage();
    }
    private void generateMessage() {
        errorModel.userMessage = context.getString(R.string.error_message_connection);
        errorModel.userTitle = context.getString(R.string.error_title);
    }
}
