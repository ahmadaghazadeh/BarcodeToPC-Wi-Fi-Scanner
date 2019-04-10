package com.github.ahmadaghazadeh.barcodescannerclient.utils.base.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.BaseViewModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.HandleException;


import dagger.android.support.DaggerAppCompatDialogFragment;



public abstract class BaseDialog<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatDialogFragment implements IDialogNavigator {


    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected T mViewDataBinding;
    protected V mViewModel;
    //@Inject
    CommonUtils commonUtils;
   // @Inject
    HandleException handleException;
    private ProgressDialog progress;

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        FragmentActivity activity = getActivity();

        performDataBinding();
        return new AlertDialog.Builder(activity)
                .setView(mViewDataBinding.getRoot())
                .create();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        progress.setCancelable(false);
        return inflater.inflate(getLayoutId(), container);
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                getLayoutId(), null, false);
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);
    }

    @Override
    public void handleError(Throwable throwable) {
        handleException.newException(throwable);
        toast(handleException.getUserMessage());
    }

    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }

    public void showProgress(Boolean flag) {
        if (progress == null) {
            FragmentActivity activity = getActivity();
            progress = new ProgressDialog(activity);
        }

        if (flag) {
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progress.setIndeterminate(flag);
        progress.setMessage(getString(R.string.please_wait));
        progress.show();
    }

    public void setProgress(String message, int pos) {
        progress.setMessage(message);
        progress.setProgress(pos);
    }

    public void hideProgress() {
        if (progress.isShowing())
            progress.dismiss();
    }

    public void dialogDismiss() {
        dismiss();
    }

}
