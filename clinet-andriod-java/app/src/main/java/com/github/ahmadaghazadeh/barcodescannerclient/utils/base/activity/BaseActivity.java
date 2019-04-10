package com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.BaseViewModel;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.common.CommonUtils;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.exception.HandleException;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity {

    public static final int MULTIPLE_PERMISSIONS = 101;
    public static final String PERMISSION_DIALOG = "PERMISSION_DIALOG";
    public static final String ERROR_DIALOG = "ERROR_DIALOG";

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected T mViewDataBinding;
    protected V mViewModel;
   // @Inject
    CommonUtils commonUtils;
   // @Inject
    HandleException handleException;
    private ProgressDialog progress;

    public abstract V getViewModel();

    public abstract int getBindingVariable();

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        performDataBinding();
    }

    public void handleError(Throwable throwable) {
        hideProgress();
        handleException.newException(throwable);
        FragmentManager manager = getSupportFragmentManager();
        Fragment frag = manager.findFragmentByTag(ERROR_DIALOG);
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
       // DialogPrompt prompt = DialogPrompt.newInstance(handleException.getUserTitle(), handleException.getUserMessage());
        //prompt.show(manager, ERROR_DIALOG);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.setLifecycleOwner(this);

    }

    public void toast(int resId) {
        commonUtils.toast(resId);
    }

    public void toast(String string) {
        commonUtils.toast(string);
    }


    public void showProgress(Boolean flag) {
        if (progress != null) {
            if (flag) {
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            }
            progress.setIndeterminate(flag);
            progress.setMessage(getString(R.string.please_wait));
            progress.show();
        }
    }

    public void setProgress(String message, int pos) {
        if (progress != null) {
            progress.setMessage(message);
            progress.setProgress(pos);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
    }

    public void hideProgress() {
        if (progress != null) {
            Activity activity = progress.getOwnerActivity();
            if (activity != null && !activity.isFinishing() && progress.isShowing()) {
                progress.dismiss();
            } else if (progress.isShowing()) {
                progress.dismiss();

            }
        }

    }

    public void finishActivity() {
        this.finish();
    }

    public void replaceFragment(FragmentManager fragmentManager, int container, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(container, fragment);
        transaction.addToBackStack(fragment.getClass().getName());
        transaction.commit();
    }

    public void changeFragment(@IdRes int resId, Fragment fragment) {
        replaceFragment(getSupportFragmentManager(),
                resId, fragment);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


}
