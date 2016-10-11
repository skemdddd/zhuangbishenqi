package com.example.administrator.zhuangbishenqi.entity;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/26.
 */
public class MyBitmapStore {
    public static Bitmap getBmp() {
        return bmp;
    }

    public static void setBmp(Bitmap bmp) {
        MyBitmapStore.bmp = bmp;
    }

    static private Bitmap bmp=null;//一定要是static的才行..

    public static Bitmap getBmp1() {
        return bmp1;
    }

    public static void setBmp1(Bitmap bmp1) {
        MyBitmapStore.bmp1 = bmp1;
    }

    static private Bitmap bmp1=null;

}

