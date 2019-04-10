package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

/**
 * Created by ferdos.s on 6/8/2017.
 */

public class RunnableModel<T> {


    private T model;

    public void setModel(T model) {
        this.model = model;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    private Throwable exception;
    public boolean hasError(){
        return exception!=null;
    }

    public RunnableModel() {

    }
    public RunnableModel(T model, Throwable exception) {
        this.model = model;
        this.exception = exception;
    }


    public T getModel() {
        return model;
    }

    public Throwable getException() {
        return exception;
    }
}
