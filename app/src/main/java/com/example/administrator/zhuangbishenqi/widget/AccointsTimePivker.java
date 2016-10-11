package com.example.administrator.zhuangbishenqi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.widget.FrameLayout;
import android.widget.NumberPicker;

import com.example.administrator.zhuangbishenqi.R;


import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/9/19.
 */

public class AccointsTimePivker extends FrameLayout {



    private int mMonth, mMinute,mDay,mHour;
    private AccointsTimePivker.OnDateTimeChangedListener mOnDateTimeChangedListener;

    public AccointsTimePivker(Context context) {
        super(context);

        /**
         * 加载布局
         */
        inflate(context, R.layout.datefourdialog, this);
        /**
         * 初始化控件
         */
        NumberPicker.OnValueChangeListener mOnMonthChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mMonth = newVal;
                onDateTimeChanged();

            }
        };

        QNumberPicker mMonthSpinner = (QNumberPicker) this.findViewById(R.id.monthpicker);
        mMonthSpinner.setMinValue(1);
        mMonthSpinner.setMaxValue(12);
        mMonthSpinner.setOnValueChangedListener(mOnMonthChangedListener);
        mMonthSpinner.setValue(7);
        setNumberPickerDividerColor(mMonthSpinner);

        NumberPicker.OnValueChangeListener mOnDayChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mDay = newVal;
                onDateTimeChanged();

            }
        };

        QNumberPicker mDaySpinner = (QNumberPicker) this.findViewById(R.id.daypeicker);
        mDaySpinner.setMinValue(1);
        mDaySpinner.setMaxValue(23);
        mDaySpinner.setOnValueChangedListener(mOnDayChangedListener);
        mDaySpinner.setValue(12);
        setNumberPickerDividerColor(mDaySpinner);

        NumberPicker.OnValueChangeListener mOnHourChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHour = newVal;
                onDateTimeChanged();

            }
        };

        QNumberPicker hourSpinner = (QNumberPicker) this.findViewById(R.id.hourPicker);
        hourSpinner.setMinValue(1);
        hourSpinner.setMaxValue(23);
        hourSpinner.setOnValueChangedListener(mOnHourChangedListener);
        hourSpinner.setValue(12);
        setNumberPickerDividerColor(hourSpinner);


        NumberPicker.OnValueChangeListener mOnMinuteChangedListener = new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mMinute= newVal;
                onDateTimeChanged();

            }
        };


        QNumberPicker minuteSpinner = (QNumberPicker) this.findViewById(R.id.minutePicker);
        minuteSpinner.setMaxValue(59);
        minuteSpinner.setMinValue(0);
        minuteSpinner.setValue(30);
        minuteSpinner.setOnValueChangedListener(mOnMinuteChangedListener);
        setNumberPickerDividerColor(minuteSpinner);




    }

    private NumberPicker.OnValueChangeListener mOnHourChangedListener = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mHour = newVal;
            onDateTimeChanged();

        }
    };

    //    函数回调；
    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(AccointsTimePivker view, int month, int day, int hour, int minute);



    }
    public void setOnDateTimeChangedListener(AccointsTimePivker.OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;
    }

    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            mOnDateTimeChangedListener.onDateTimeChanged(this ,mMonth, mDay,mHour,mMinute);
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
