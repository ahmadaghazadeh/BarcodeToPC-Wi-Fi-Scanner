package com.github.ahmadaghazadeh.barcodescannerclient.utils.exception;

import android.content.Context;
import android.database.SQLException;
import android.os.NetworkOnMainThreadException;


import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.model.ErrorModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;



public class HandleException extends BaseException {
    private List<Throwable> list;
    private Context context;


    public void newException(Throwable exception) {
        errorModel = new ErrorModel();
        this.list = new LinkedList<>();
        this.list.add(exception);
        generateMessage(context, this.list.size()-1);
    }


    public void addException(Throwable exception) {
        this.list.add(exception);
        generateMessage(context, this.list.size()-1);
    }

    @Inject
    public HandleException(Context context) {
        super();
        this.list = new LinkedList<>();
        this.context=context;
    }

    private  void generateMessage(Context context,int index){
        Throwable exception=list.get(index);
        if(exception instanceof ApiException){
            errorModel = ((ApiException) exception).errorModel;
        }
        else if(exception instanceof SocketTimeoutException){
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += context.getString(R.string.error_message_timeout)+" \n";
            errorModel.systemMessage += "SocketTimeoutException"+" \n";
        }
        else if(exception instanceof MalformedURLException){
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += context.getString(R.string.error_message_url)+" \n";
            errorModel.systemMessage += "MalformedURLException"+" \n";
        }
        else if(exception instanceof FileNotFoundException){
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += context.getString(R.string.error_file_not_found)+" \n";
            errorModel.systemMessage += "FileNotFoundException"+" \n";
        }
        else if(exception instanceof IOException){
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += context.getString(R.string.error_message_io)+" \n";
            errorModel.systemMessage += "IOException"+" \n";
        }
        else if(exception instanceof SQLException){
            DatabaseException ex = new DatabaseException((SQLException) exception);
            errorModel.userTitle += ex.errorModel.userTitle+" \n";
            errorModel.userMessage += ex.errorModel.userMessage+" \n";
            errorModel.systemMessage += ex.errorModel.systemMessage+" \n";
        }
        else if(exception instanceof NetworkOnMainThreadException){
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += context.getString(R.string.error_main_thread_)+" \n";
            errorModel.systemMessage += "NetworkOnMainThreadException"+" \n";
        }
         else {
            errorModel.userTitle += context.getString(R.string.error_title)+" \n";
            errorModel.userMessage += exception.getMessage()+" \n";
            //errorModel.systemMessage += " \n";
        }
    }
}
