package com.example.customdialog;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Written by Joe Fachrizal
 **/
public class CustomDialogDatepicker extends Dialog {
    private Activity activity;
    @BindView(R.id.btn_yes)
    Button btnYes;
    @BindView(R.id.btn_no)
    Button btnNo;
    @BindView(R.id.notification)
    LinearLayout notification;
    @BindView(R.id.datePicker)
    DatePicker datePicker;
    private onConfirmationListener listener;
    private DialogStyle dialogStyle;

    public CustomDialogDatepicker(Context context, Activity activity, DialogStyle dialogStyle) {
        super(context);
        this.activity = activity;
        this.dialogStyle = dialogStyle;
    }


    public void setListener(onConfirmationListener listener) {
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_datepicker);
        ButterKnife.bind(this);

        final Calendar calendar = Calendar.getInstance();
        int DAY = calendar.get(Calendar.DAY_OF_MONTH);
        int MONTH = calendar.get(Calendar.MONTH);
        int YEAR = calendar.get(Calendar.YEAR);

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
        if (listener != null) {
            btnYes.setOnClickListener(v -> {
                listener.onSetClicked();
                Toast.makeText(activity, datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear(), Toast.LENGTH_SHORT).show();
            });

            btnNo.setOnClickListener(v -> {

            });
        }
    }

    public interface onConfirmationListener {
        void onSetClicked();

        void onCancelClicked();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    Toast.makeText(activity, "selected date is " + view.getYear() +
                            " / " + (view.getMonth() + 1) +
                            " / " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();
                }
            };
}