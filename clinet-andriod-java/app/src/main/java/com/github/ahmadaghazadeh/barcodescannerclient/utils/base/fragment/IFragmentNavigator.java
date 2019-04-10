package com.github.ahmadaghazadeh.barcodescannerclient.utils.base.fragment;


import com.github.ahmadaghazadeh.barcodescannerclient.utils.base.INavigator;

public interface IFragmentNavigator extends INavigator {

    void setToolbarProgressBar(boolean isIndeterminate, int progress);
}
