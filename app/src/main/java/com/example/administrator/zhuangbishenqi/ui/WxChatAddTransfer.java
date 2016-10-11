package com.example.administrator.zhuangbishenqi.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.widget.DateTimePickerDialog;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/8/31.
 *
 *
 * 转账
 */

public class WxChatAddTransfer extends WxchatAddHongBao {
    @Override
    public void initWidget() {
        super.initWidget();
        super.edi_redinformation.setHint("转账金额");
        super.typepictires=7;
        super.tv_title.setText("增加转账消息");
        super.edi_redinformation.setInputType(InputType.TYPE_CLASS_NUMBER);
        super.tv_envelope.setText("已收钱");

    }
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.btn_ture:

                if(!edi_redinformation.getText().toString().equals("")){
                    HashMap<String,String> map=new HashMap<>();
                    if (typepictires == 7) {
                        SharedPreferences preference = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
                        map.put("yourname",preference.getString("myName","苏娜"));
                    }else{
                        SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
                        map.put("yourname",preference.getString("myName","小雅"));
                    }
                   ;
                    if(typepictires==7){
                        map.put("type","type7");
                    }else{
                        map.put("type","type8");
                    }
                    map.put("typered",typered);
                    map.put("time",tv_time1.getText().toString());
                    WxChat.mData.add(map);
                    map.put("redinformation",edi_redinformation.getText().toString());
                    Logger.i("1111111"+WxChat.mData.size());
                    finish();
                }else {
                    Toast.makeText(this,"转账金额不能为空",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.LL_time:
                new DateTimePickerDialog(WxChatAddTransfer.this,System.currentTimeMillis(),2,tv_time1).show();
                break;
            case R.id.rbtn_my:
                typepictires=7;
                Logger.i("类型"+typepictires);
                break;
            case R.id.rbtn_other:
                typepictires=8;
                Logger.i("类型"+typepictires);
                break;
        }
        Logger.i("type"+type);
    }
}
