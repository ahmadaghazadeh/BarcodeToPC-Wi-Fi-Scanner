package com.github.ahmadaghazadeh.barcodescannerclient.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.StringRes;
import android.support.v4.widget.CircularProgressDrawable;
import android.text.Html;
import android.text.Spanned;
import android.util.Patterns;
import android.widget.Toast;


import com.github.ahmadaghazadeh.barcodescannerclient.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class CommonUtils {

    public static Spanned fromHtml(String string) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(string);
        }
    }



    public static int[] colorScheme = {R.color.md_amber_500, R.color.md_blue_500, R.color.md_red_500};
    public static int[] tintColorArray = new int[]{
            R.color.md_amber_300,
            R.color.md_blue_300,
            R.color.md_green_300,
            R.color.md_red_300,
            R.color.md_indigo_300,
            R.color.md_blue_grey_300,
            R.color.md_brown_300,
            R.color.md_cyan_300,
            R.color.md_deep_orange_300,
            R.color.md_deep_purple_300,
            R.color.md_grey_300,
            R.color.md_light_blue_300,
            R.color.md_yellow_300,
            R.color.md_teal_300,
    };
    private Context context;

    public CommonUtils(Context context) {
        this.context = context;
    }

    public int getDrawable(String resName) {
        return context.getResources().getIdentifier(resName, "drawable", context.getPackageName());
    }

    public static String getGson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * This method deserializes the Json read from the specified Class
     *
     * @param <T>     the type of the desired object
     * @param gsonStr the root of the parse
     * @return an object of type T from the json. Returns {@code null} if {@code json} is {@code null}.
     * <pre>
     *     {@code
     *      TypeToken<FileContentModel> typeToken = new TypeToken<FileContentModel>() {};
     *     }
     * </pre>
     */
    public static <T> T getObject(String gsonStr, TypeToken<T> typeToken) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        T obj = gson.fromJson(gsonStr,
                typeToken.getType());
        return obj;
    }

    public static void exportDatabase(Context context, String databaseName) {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + context.getPackageName() + "//databases//" + databaseName + "";
                String currentDBPath_shm = "//data//" + context.getPackageName() + "//databases//" + databaseName + "-shm";
                String currentDBPath_wal = "//data//" + context.getPackageName() + "//databases//" + databaseName + "-wal";
                String backupDBPath = databaseName;
                String backupDBPath_shm = databaseName + "-shm";
                String backupDBPath_wal = databaseName + "-wal";

                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }

                File currentDB_shm = new File(data, currentDBPath_shm);
                File backupDB_shm = new File(sd, backupDBPath_shm);

                if (currentDB_shm.exists()) {
                    FileChannel src = new FileInputStream(currentDB_shm).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB_shm).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }

                File currentDB_wal = new File(data, currentDBPath_wal);
                File backupDB_wal = new File(sd, backupDBPath_wal);

                if (currentDB_wal.exists()) {
                    FileChannel src = new FileInputStream(currentDB_wal).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB_wal).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {

        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }




    public Context getContext() {
        return context;
    }

    public boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void toast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public void toast(@StringRes int resId) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show();
    }

    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }

    public CircularProgressDrawable getCircularProgressDrawable() {
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setColorSchemeColors(
                R.color.md_amber_500,
                R.color.md_blue_500,
                R.color.md_green_500,
                R.color.md_red_500
        );
        drawable.setStrokeWidth(5f);
        drawable.setCenterRadius(30f);
        drawable.start();
        return drawable;
    }

    public void playSound(int row) {
        MediaPlayer mp = MediaPlayer.create(context, row);
        mp.start();
    }
}
