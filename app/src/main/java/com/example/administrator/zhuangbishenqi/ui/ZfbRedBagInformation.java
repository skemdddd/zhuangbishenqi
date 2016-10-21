package com.example.administrator.zhuangbishenqi.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.mediav.ads.sdk.adcore.Mvad;

/**
 * Created by Administrator on 2016/7/27.
 */
public class ZfbRedBagInformation  extends WxRedInformation{
    @Override
    public void getRedView(Class lasss) {
        Intent intent1 = new Intent(this,ZfbRedBag.class);
        Bitmap image1 = ((BitmapDrawable) img_head.getDrawable()).getBitmap();
        MyBitmapStore.setBmp(image1);
        intent1.putExtra("name", tv_name.getText());
        intent1.putExtra("money", edt_money.getText().toString());
        intent1.putExtra("information", edt_wx_information.getText().toString());
        final String adSpaceid = "aFub09x2i4";
        Mvad.showFloatbannerAd(this,adSpaceid,false,Mvad.FLOAT_BANNER_SIZE.SIZE_DEFAULT,Mvad.FLOAT_LOCATION.BOTTOM);
        startActivity(intent1);
    }
}
