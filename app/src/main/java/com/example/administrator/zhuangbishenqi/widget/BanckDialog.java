package com.example.administrator.zhuangbishenqi.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 * Created by Administrator on 2016/9/9.
 */

public class BanckDialog extends AlertDialog implements
        DialogInterface.OnClickListener {
    private int mbanck;
     private TextView textView;
    public BanckDialog(Context context, TextView textView) {
        super(context);
        this.textView=textView;
        BanckPicker dateTimePicker = new BanckPicker(context);
        dateTimePicker.OnBanckTimeChangedListener(new BanckPicker.OnBanckTimeChangedListener() {
            @Override
            public void onDateTimeChanged(BanckPicker view, int banck) {
               mbanck=banck;
            }
        });

        setView(dateTimePicker);
        setButton("确定", this);
        setButton2("取消", (OnClickListener) null);

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(mbanck==0){
            textView.setText(Name.arrd[mbanck]);
        }else{
            textView.setText(Name.arrd[mbanck-1]);
        }


    }
}
