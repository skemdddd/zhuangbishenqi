package com.example.administrator.zhuangbishenqi.widget;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.Name;

import java.lang.reflect.Field;

public class DateTimePicker extends FrameLayout {
    private QNumberPicker mHourSpinner;
    private QNumberPicker mMinuteSpinner;
    private int mHour, mMinute,mSecond;
    private OnDateTimeChangedListener mOnDateTimeChangedListener;

    public DateTimePicker(Context context,int type) {
        super(context);

        /**
         * 加载布局
         */
        inflate(context, R.layout.datedialog, this);
        /**
         * 初始化控件
         */
        OnValueChangeListener onMinuteChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mMinute = newVal;
                onDateTimeChanged();

            }
        };
        OnValueChangeListener onHourChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHour = newVal;
                onDateTimeChanged();

            }
        };
        if(type ==1 ){
            mHourSpinner = (QNumberPicker) this.findViewById(R.id.hourpicker);
//        mHourSpinner.setDisplayedValues(arr);
            mHourSpinner.setMinValue(1);
            mHourSpinner.setMaxValue(23);
            mHourSpinner.setOnValueChangedListener(onHourChangedListener);
            mHourSpinner.setValue(12);
            setNumberPickerDividerColor(mHourSpinner);

            mMinuteSpinner = (QNumberPicker) this.findViewById(R.id.minuteicker);
            mMinuteSpinner.setMaxValue(59);
            mMinuteSpinner.setMinValue(0);
            mMinuteSpinner.setValue(30);
            mMinuteSpinner.setOnValueChangedListener(onMinuteChangedListener);
            setNumberPickerDividerColor(mMinuteSpinner);



        }else if( type ==2){
            mHourSpinner = (QNumberPicker) this.findViewById(R.id.hourpicker);
            mHourSpinner.setDisplayedValues(Name.arr);
            mHourSpinner.setMinValue(1);
            mHourSpinner.setMaxValue(Name.arr.length);
            mHourSpinner.setOnValueChangedListener(onHourChangedListener);
            mHourSpinner.setValue(1);
            setNumberPickerDividerColor(mHourSpinner);

            mMinuteSpinner = (QNumberPicker) this.findViewById(R.id.minuteicker);
            mMinuteSpinner.setMaxValue(23);
            mMinuteSpinner.setMinValue(1);
            mMinuteSpinner.setValue(12);
            mMinuteSpinner.setOnValueChangedListener(onMinuteChangedListener);
            setNumberPickerDividerColor(mMinuteSpinner);
        }


        QNumberPicker secondSpinner = (QNumberPicker) this.findViewById(R.id.secondPicker);
        secondSpinner.setMaxValue(59);
        secondSpinner.setMinValue(0);
        secondSpinner.setValue(30);
        OnValueChangeListener onSecondChangedListener = new OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSecond = newVal;
                onDateTimeChanged();

            }
        };
        secondSpinner.setOnValueChangedListener(onSecondChangedListener);
        setNumberPickerDividerColor(secondSpinner);
        mHour = mHourSpinner.getValue();
        mMinute = mMinuteSpinner.getValue();
        mSecond= secondSpinner.getValue();
        onDateTimeChanged();


    }

    //    函数回调；
    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker view, int hour, int minute,int second);



}
    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }

    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            mOnDateTimeChangedListener.onDateTimeChanged(this ,mHour, mMinute,mSecond);
        }
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields)
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