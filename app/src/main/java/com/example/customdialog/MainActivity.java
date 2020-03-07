package com.example.customdialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private CustomDialog customDialog;
    private CustomDialogDatepicker customDialogDatepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnYesNo, R.id.btnOk, R.id.datePicker})
    public void Confirm(View view) {
        switch (view.getId()) {
            case R.id.btnYesNo:
                customDialog = new CustomDialog(this, this, DialogStyle.CONFIRMATION_YES_NO);
                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialog.setListener(new CustomDialog.onConfirmationListener() {
                    @Override
                    public void onConfirmationYes() {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        customDialog.notification.setVisibility(View.VISIBLE);
                        customDialog.setLottieAnimAsset("lottie-anim/success.json");
                    }

                    @Override
                    public void onConfirmationNo() {
                        Toast.makeText(MainActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                        customDialog.notification.setVisibility(View.VISIBLE);
                        customDialog.setLottieAnimAsset("lottie-anim/load-error.json");
                    }
                });
                customDialog.show();
                break;
            case R.id.btnOk:
                customDialog = new CustomDialog(this, this, DialogStyle.SINGLE_CONFIRMATION);
                customDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialog.setListener(new CustomDialog.onConfirmationListener() {
                    @Override
                    public void onConfirmationYes() {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        customDialog.notification.setVisibility(View.VISIBLE);
                        customDialog.setLottieAnimAsset("lottie-anim/success.json");
                    }

                    @Override
                    public void onConfirmationNo() {
                    }
                });
                customDialog.show();
                break;
            case R.id.datePicker:
                customDialogDatepicker = new CustomDialogDatepicker(this,this, DialogStyle.CONFIRMATION_YES_NO);
                customDialogDatepicker.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                customDialogDatepicker.setListener(new CustomDialogDatepicker.onConfirmationListener() {
                    @Override
                    public void onSetClicked() {

                    }

                    @Override
                    public void onCancelClicked() {

                    }
                });

                customDialogDatepicker.show();
                break;
        }

    }

}
