package com.github.ahmadaghazadeh.barcodescannerclient.utils.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by ferdos.s on 6/1/2017.
 */

public class Device {
    @SuppressLint("MissingPermission")
    public static long getIMEI(Context context) {
        TelephonyManager telephonyManager1 = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        long imei=0;
        if(!Build.FINGERPRINT.contains("generic"))
        {
            imei = Long.parseLong(telephonyManager1.getDeviceId());
        }
        return imei;
    }

    public static int getPixelsFromDp(Context context, int dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (60 * scale + 0.5f);
    }

    public static int getBatteryPercentage(Context context) {

        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, iFilter);

        int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
        int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

        float batteryPct = level / (float) scale;

        return (int) (batteryPct * 100);
    }
}
