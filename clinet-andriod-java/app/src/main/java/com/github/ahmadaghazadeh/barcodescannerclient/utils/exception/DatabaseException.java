package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;

import com.github.ahmadaghazadeh.barcodescannerclient.R;


public class DatabaseException extends BaseException {

    public Context context;
    private SQLException exception;
    protected DatabaseException(SQLException exception){
        this.exception = exception;

        if(exception instanceof SQLiteConstraintException){
            errorModel.userTitle = context.getString(R.string.error_title);
            errorModel.userMessage = context.getString(R.string.error_message_constraint);
            errorModel.systemMessage = exception.getMessage();
        }
        else{
            errorModel.userTitle = context.getString(R.string.error_title);
            errorModel.userMessage = context.getString(R.string.error_message_sqlite);
            errorModel.systemMessage = exception.getMessage();
        }
    }
    protected DatabaseException(Context context,String exception){
        this.exception = new SQLException(exception);
        errorModel.userTitle = context.getString(R.string.error_title);
        errorModel.userMessage = context.getString(R.string.error_message_sqlite);
        errorModel.systemMessage = exception;
    }
}
