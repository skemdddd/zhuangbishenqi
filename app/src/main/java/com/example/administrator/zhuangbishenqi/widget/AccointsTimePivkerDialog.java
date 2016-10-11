package com.example.administrator.zhuangbishenqi.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/19.
 */

public class AccointsTimePivkerDialog extends AlertDialog implements DialogInterface.OnClickListener{

    private String  mMonth, mMinute,mDay,mHour;
    private TextView textView;
    @SuppressWarnings("deprecation")
    public AccointsTimePivkerDialog(Context context,  TextView textView) {
        super(context);
        this.textView=textView;
        AccointsTimePivker mAccointsTimePivker = new AccointsTimePivker(context);

        mAccointsTimePivker.setOnDateTimeChangedListener(new AccointsTimePivker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(AccointsTimePivker view, int month, int day, int hour, int minute) {
                mMonth =String.valueOf(month);
                mDay=String.valueOf(day);
                mHour=String.valueOf(hour);
                mMinute=String.valueOf(minute);
            }
        });
        setView(mAccointsTimePivker);
        setButton("确定", this);
        setButton2("取消", (DialogInterface.OnClickListener) null);

    }



    public void onClick(DialogInterface dialog, int which ) {
        if(mMonth.length()<2 ) {
            mMonth = "0" + mMonth;
        }
        if(mMinute.length()<2) {
            mMinute = "0" + mMinute;
        }
        if(mHour.length()<2) {
            mHour = "0" + mHour;
        }

        if(mDay.length()<2) {
            mDay = "0" + mDay;
        }
        textView.setText(2016+"-"+mMonth+"-"+mDay+" "+mHour+":" +
                ""+mMinute);
    }
}
