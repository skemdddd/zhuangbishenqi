package com.example.administrator.zhuangbishenqi.widget;

/**
 * Created by Administrator on 2016/8/1.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.ui.QqRedInformation;
import com.orhanobut.logger.Logger;


public class DateTimePickerDialog extends AlertDialog implements
        OnClickListener {
    private int type;
    private String mHour, mMinute,mSecond;
    private TextView textView;
      @SuppressWarnings("deprecation")
    public DateTimePickerDialog(Context context, long date, int type, TextView textView) {
        super(context);
          this.type=type;
          this.textView=textView;
          DateTimePicker dateTimePicker = new DateTimePicker(context, type);
          dateTimePicker
                  .setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
                      @Override
                      public void onDateTimeChanged(DateTimePicker view, int hour, int minute, int second) {
                          Logger.i("时间"+hour);
                            mHour =String.valueOf(hour);
                            mMinute=String.valueOf(minute);
                            mSecond=String.valueOf(second);

                      }
                  });
         setView(dateTimePicker);
        setButton("确定", this);
        setButton2("取消", (OnClickListener) null);

    }



    public void onClick(DialogInterface dialog, int which ) {
        if(mHour!= null){
            getTime(type);
        }

    }
    private void getTime(int type){

        if(type ==1 ){
              if(mHour.length()<2 ) {
                mHour = "0" + mHour;
            }
            if(mSecond.length()<2) {
                mSecond = "0" + mSecond;
            }

            if(mMinute.length()<2) {
                mMinute = "0" + mMinute;
            }
            QqRedInformation.tv_time.setText(mHour+":"+mMinute+":"+mSecond);

        }else if(type == 2){
            if(mHour.equals("0")){
                mHour="null";
            }
            if(mSecond.length()<2) {
                mSecond = "0" + mSecond;
            }
            if(mMinute.length()<2) {
                mMinute = "0" + mMinute;
            }
            try{
                textView.setText(Name.arr[Integer.parseInt(mHour)-1]+" "+mMinute+":"+mSecond);
            }catch (Exception e){
               textView.setText(mMinute+":"+mSecond);
            }

        }

    }
}