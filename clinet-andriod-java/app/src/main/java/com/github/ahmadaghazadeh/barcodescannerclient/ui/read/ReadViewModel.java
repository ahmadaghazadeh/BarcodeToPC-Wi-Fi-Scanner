package com.github.ahmadaghazadeh.barcodescannerclient.ui.read;


import android.arch.lifecycle.MutableLiveData;

import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.data.remote.IRepository;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.BaseViewModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableIn;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableMethod;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.RunnableModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.BaseException;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.UncaughtException;

import javax.inject.Inject;


public class ReadViewModel extends BaseViewModel<IReadNavigator> {

    public MutableLiveData<String> code = new MutableLiveData<>();
    public MutableLiveData<String> serverUrl = new MutableLiveData<>();
    public MutableLiveData<Boolean> isActiveServer= new MutableLiveData<>();
    @Inject
    public IRepository repository;

    @Inject
    public CommonUtils commonUtils;


    @Inject
    public ReadViewModel() {
        isActiveServer.setValue(false);
    }



    public void setCode(String text) {
        code.setValue(text);
        getNavigator().showProgress(false);
        RunnableMethod<Object, RunnableModel> runnableMethod = (param, onProgressUpdate) -> {
            RunnableModel runnableModel = new RunnableModel();
            try {
                repository.SendQrCodeData(text);
            } catch (BaseException e) {
                runnableModel.setException(e);
            } catch (Exception ex) {
                runnableModel.setException(new UncaughtException(commonUtils.getContext(), ex));
            }
            return runnableModel;
        };

        RunnableIn<RunnableModel> post = (param) -> {
            if (param.hasError()) {
                getNavigator().handleError(param.getException());
            } else {
               // getNavigator().toast(commonUtils.getString(R.string.send_data));
            }
            getNavigator().hideProgress();

        };
        runAsyncTask(runnableMethod, post);


    }

    public void setServerUrl(String text) {
        isActiveServer.setValue(true);
        serverUrl.setValue(text);
    }

    public void onClickConnectToServer() {
        getNavigator().connectToServer();
    }

    public void onClickStartRead() {
        getNavigator().startRead();
    }
}
