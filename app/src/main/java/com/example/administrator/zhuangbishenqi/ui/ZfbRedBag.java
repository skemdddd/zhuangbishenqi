package com.example.administrator.zhuangbishenqi.ui;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;
import java.text.SimpleDateFormat;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/7/27
 * 支付宝红包.
 */
public class ZfbRedBag extends WxRedBag {
    TextView tv_name;   //微信名字
    ImageButton ibtn_back;   //返回按钮
    TextView  tv_wx_information;//自定义留言
    CircleImageView img_heard;//微信头像
    TextView tv_wx_money;    //红包金额
    TextView zfb_tv_timeid;


    @Override
    public void initWidget() {
        setContentView(R.layout.activity_zfb_red_bag);
        findId();
        ibtn_back.setOnClickListener(this);
        Intent intent = getIntent();
        tv_name.setText(intent.getStringExtra("name"));
        img_heard.setImageBitmap(MyBitmapStore.getBmp());
        tv_wx_money.setText(Name.getStringNum(intent.getStringExtra("money")));
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(new java.util.Date());
        String aa="";
        for(int i=0 ;i<11;i++){
            Random mrandom = new Random();
            String randomNumber = String.valueOf(mrandom.nextInt(100-1));
            if(randomNumber.length()<2){
              aa =aa+"0"+randomNumber;
            }else {
              aa=aa+randomNumber;
            }

        }
        zfb_tv_timeid.setText("红包编号:"+ date+"000630"+aa);
        if(intent.getStringExtra("information").equals("")) {
            tv_wx_information.setText(R.string.wx_information);
        }else{
            tv_wx_information.setText(intent.getStringExtra("information"));
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                ibtn_back.setBackgroundColor(ContextCompat.getColor(this,R.color.SystemBarTint));
                finish();
                break;
        }
    }

    @Override
    public void findId() {
        tv_name= (TextView) findViewById(R.id.tvwx_name);
        tv_wx_information= (TextView) findViewById(R.id.tv_wx_information);
        ibtn_back= (ImageButton) findViewById(R.id.imageButton);
        img_heard= (CircleImageView) findViewById(R.id.wx_img_head_circle);
        tv_wx_money= (TextView) findViewById(R.id.tvwx_money);
        zfb_tv_timeid= (TextView) findViewById(R.id.tv_tiemid);

    }
}
