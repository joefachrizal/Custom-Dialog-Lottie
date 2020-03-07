package com.example.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Written by Joe Fachrizal
 **/
public class CustomDialog extends Dialog {
    private Activity activity;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.lavNotification)
    LottieAnimationView lavNotification;
    @BindView(R.id.notification)
    LinearLayout notification;
    private onConfirmationListener listener;
    private DialogStyle dialogStyle;
    private String lottieAnimAsset;

    public CustomDialog(Context context, Activity activity, DialogStyle dialogStyle) {
        super(context);
        this.activity = activity;
        this.dialogStyle = dialogStyle;
    }

    public void setLottieAnimAsset(String lottieAnimAsset) {
        this.lottieAnimAsset = lottieAnimAsset;
    }

    public void setListener(onConfirmationListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);
        switch (dialogStyle) {
            case CONFIRMATION_YES_NO: {
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.VISIBLE);
                break;
            }
            case SINGLE_CONFIRMATION: {
                btnYes.setVisibility(View.VISIBLE);
                btnNo.setVisibility(View.GONE);
                break;
            }
        }
        if (lottieAnimAsset != null && !lottieAnimAsset.equals("")) {
            lavNotification.setAnimation(lottieAnimAsset);
        }
        if (listener != null) {
            btnYes.setOnClickListener(v -> {
                listener.onConfirmationYes();
                lavNotification.setScale(1f);
                lavNotification.setAnimation(lottieAnimAsset);
                lavNotification.playAnimation();
            });

            btnNo.setOnClickListener(v -> {
                listener.onConfirmationNo();
                lavNotification.setScale(2f);
                lavNotification.setAnimation(lottieAnimAsset);
                lavNotification.playAnimation();
            });
        }
    }

    public interface onConfirmationListener {
        void onConfirmationYes();

        void onConfirmationNo();
    }
}