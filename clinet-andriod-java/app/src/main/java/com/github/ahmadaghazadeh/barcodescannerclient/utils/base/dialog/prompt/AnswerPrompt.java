package com.github.ahmadaghazadeh.barcodescannerclient.utils.base.dialog.prompt;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DialogTitle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.github.ahmadaghazadeh.barcodescannerclient.R;

import dagger.android.support.DaggerAppCompatDialogFragment;

public class AnswerPrompt extends DaggerAppCompatDialogFragment {

    private static final String MESSAGE = "MESSAGE";
    private static final String TITLE = "TITLE";
    String title;
    String message;

    public static AnswerPrompt newInstance(String title, String message) {
        AnswerPrompt dialog = new AnswerPrompt();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(MESSAGE, message);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(TITLE);
            message = getArguments().getString(MESSAGE);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        FragmentActivity activity = getActivity();

        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setNegativeButton(R.string.i_understand, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setMessage(message)
                .create();
        alertDialog.setIcon(R.mipmap.ic_launcher_round);

        @SuppressLint("RestrictedApi")
        DialogTitle titleView = new DialogTitle(activity);
        titleView.setText(title);
        titleView.setPadding(32,
                32,
                32,
                16);
        alertDialog.setCustomTitle(titleView);
        return alertDialog;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commit();

    }

}
