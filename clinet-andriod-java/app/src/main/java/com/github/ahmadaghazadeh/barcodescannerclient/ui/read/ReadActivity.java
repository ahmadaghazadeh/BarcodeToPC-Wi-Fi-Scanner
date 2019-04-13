package com.github.ahmadaghazadeh.barcodescannerclient.ui.read;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.github.ahmadaghazadeh.barcodescannerclient.app.BundleNames;
import com.github.ahmadaghazadeh.barcodescannerclient.databinding.ActivityReadBinding;
import com.github.ahmadaghazadeh.barcodescannerclient.ui.connect.ConnectActivity;
import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.activity.BaseActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;


import javax.inject.Inject;

public class ReadActivity extends BaseActivity<ActivityReadBinding, ReadViewModel>
        implements IReadNavigator, QRCodeReaderView.OnQRCodeReadListener {

    @Inject
    ViewModelProvider.Factory factory;
    private QRCodeReaderView qrCodeReaderView;

    public static Intent newInstance(Context context, int id) {
        Intent intent = new Intent(context, ReadActivity.class);
        intent.putExtra(BundleNames.Id, id);
        return intent;
    }


    @Override
    public ReadViewModel getViewModel() {
        return ViewModelProviders.of(this, factory).get(ReadViewModel.class);
    }

    @Override
    public int getBindingVariable() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        mViewModel.setNavigator(this);
    }

    private void initPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {

                    }
                }).check();


    }

    private void initQRCode() {
        qrCodeReaderView = mViewDataBinding.qrdecoderview;
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();
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

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (qrCodeReaderView != null) {
            qrCodeReaderView.stopCamera();
        }
    }

    @Override
    public void startRead() {
        initQRCode();
        if (qrCodeReaderView != null) {
            qrCodeReaderView.startCamera();
        }
    }

    @Override
    public void connectToServer() {
        startActivityForResult(ConnectActivity.newInstance(this), ConnectActivity.RequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ConnectActivity.RequestCode && resultCode == RESULT_OK && data!=null) {
            mViewModel.setServerUrl(data.getStringExtra(BundleNames.Url));
        }
    }
}
