package com.github.ahmadaghazadeh.barcodescannerclient.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;


import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.app.BundleNames;
import com.github.ahmadaghazadeh.barcodescannerclient.databinding.ActivityMainBinding;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements IMainNavigator, QRCodeReaderView.OnQRCodeReadListener {

    @Inject
    ViewModelProvider.Factory factory;
    private QRCodeReaderView qrCodeReaderView;
    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(BundleNames.Id, id);
        return intent;
    }


    @Override
    public MainViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(MainViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);

    }



    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        mViewModel.setCode(text);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }
}
