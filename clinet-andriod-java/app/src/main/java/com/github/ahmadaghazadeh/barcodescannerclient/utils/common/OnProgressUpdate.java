package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

/**
 * Created by aghazadeh.a on 1/14/2018.
 */

public interface OnProgressUpdate {
    void onProgressUpdate(String title);
    void onProgressUpdate(String title, String message);
}
