package com.github.ahmadaghazadeh.barcodescannerclient.utils.base.fragment;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.SharedMainViewModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity.BaseActivity;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.HandleException;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends FragmentBaseViewModel> extends DaggerFragment {

    public static final String ERROR_DIALOG = "ERROR_DIALOG";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected T mViewDataBinding;
    protected V mViewModel;

    SharedMainViewModel sharedMainViewModel;

    BaseActivity mActivity;

    //@Inject
    CommonUtils commonUtils;

    //@Inject
    HandleException handleException;


    private ProgressDialog progress;

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
        sharedMainViewModel= ViewModelProviders.of(getBaseActivity()).get(SharedMainViewModel.class);
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


    public void handleError(Throwable throwable) {
        handleException.newException(throwable);
        FragmentManager manager = mActivity.getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag(ERROR_DIALOG);
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
       //TO DO show error message
    }


    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }


    public void showProgress(Boolean flag) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            mActivity = (BaseActivity) getActivity();
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
            mViewDataBinding.setLifecycleOwner(this);
        }catch (Exception ex){
            ex.getMessage();
        }
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }


    public void setToolbarProgressBar(boolean isIndeterminate, int progress) {
        sharedMainViewModel.setModel(isIndeterminate,progress);
    }

    public void hideKeyboard(){
        View view = getBaseActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
