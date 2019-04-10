package com.github.ahmadaghazadeh.barcodescannerclient.utils.base;

import android.arch.lifecycle.ViewModel;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableIn;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableMethod;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.SimpleAsyncTask;

import java.lang.ref.WeakReference;


public abstract class BaseViewModel<N extends INavigator> extends ViewModel {

    private WeakReference<N> mNavigator;

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

    public void runDialogAsyncTask(RunnableMethod method, RunnableIn post) {

        RunnableMethod pre = (param, onProgressUpdate) -> {
            getNavigator().showProgress(false);
            return null;
        };

        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().handleError(param.getException());
            }
            if(post!=null){
                post.run(param);
            }
            getNavigator().hideProgress();
        };

        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(pre, method, postInternal);
        simpleAsyncTask.execute();
    }

    public void runAsyncTask(RunnableMethod method, RunnableIn post) {
        RunnableIn<RunnableModel> postInternal = (param) -> {
            if (param.getException() != null) {
                getNavigator().toast(param.getException().toString());
            }
            if(post!=null){
                post.run(param);
            }
        };

        SimpleAsyncTask simpleAsyncTask = new SimpleAsyncTask(null, method, postInternal);
        simpleAsyncTask.execute();
    }
}
