package com.example.administrator.zhuangbishenqi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.Name;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/9/9.
 */

public class BanckPicker  extends FrameLayout {
    private int banck;
    private BanckPicker.OnBanckTimeChangedListener mOnBanckTimeChangedListener;
    public BanckPicker(Context context) {
        super(context);

        /**
         * 加载布局
         */
        inflate(context, R.layout.change_banck, this);
        /**
         * 初始化控件
         */

//        mHourSpinner.setDisplayedValues(Name.arr);
//        mHourSpinner.setMinValue(1);
//        mHourSpinner.setMaxValue(Name.arr.length);
//        mHourSpinner.setOnValueChangedListener(mOnHourChangedListener);
//        mHourSpinner.setValue(1);
        QNumberPicker change_banck = (QNumberPicker) this.findViewById(R.id.change_banck);
        change_banck.setDisplayedValues(Name.arrd);
        change_banck.setMinValue(1);
        change_banck.setMaxValue(Name.arrd.length);
        change_banck.setValue(1
        );
        change_banck.setOnValueChangedListener(mBanckChangedListener);
        setNumberPickerDividerColor(change_banck);
        banck = change_banck.getValue();
        onDateTimeChanged();


    }

    private NumberPicker.OnValueChangeListener mBanckChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            banck = newVal;
            onDateTimeChanged();

        }
    };


    //    函数回调；
    public interface OnBanckTimeChangedListener {
        void onDateTimeChanged(BanckPicker view,int banck);
    }
    public void OnBanckTimeChangedListener(BanckPicker.OnBanckTimeChangedListener callback) {
        mOnBanckTimeChangedListener = callback;
    }

    private void onDateTimeChanged() {
        if (mOnBanckTimeChangedListener != null) {
            mOnBanckTimeChangedListener.onDateTimeChanged(BanckPicker.this ,banck);
        }
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(numberPicker, new ColorDrawable(this.getResources().getColor(R.color.colorGray)));
                } catch (IllegalArgumentException | Resources.NotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }




}
