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
 * Created by Administrator on 2016/9/1
 */

public class WxChatAddVoice extends WxchatAddHongBao {
    @Override
    public void initWidget() {
        super.initWidget();
        super.edi_redinformation.setHint("声音长度");
        super.typepictires=9;
        super.tv_title.setText("增加语音消息");
        super.edi_redinformation.setInputType(InputType.TYPE_CLASS_NUMBER);
        super.tv_envelope.setText("已收听");
    }
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.btn_ture:
                if(!edi_redinformation.getText().toString().equals("")){
                    if(Integer.parseInt(edi_redinformation.getText().toString())<=60){
                        HashMap<String,String> map=new HashMap<>();
                        if (typepictires == 9) {
                            SharedPreferences preference = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
                            map.put("yourname",preference.getString("myName","苏娜"));
                        }else{
                            SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
                            map.put("yourname",preference.getString("myName","小雅"));
                        }
                        if(typepictires==9){
                            map.put("type","type9");
                        }else{
                            map.put("type","type10");
                        }
                        map.put("typered",typered);
                        map.put("time",tv_time1.getText().toString());
                        WxChat.mData.add(map);
                        map.put("redinformation",edi_redinformation.getText().toString());
                        Logger.i("1111111"+WxChat.mData.size());
                        finish();
                    }else {
                        Toast.makeText(this,"声音长度不能超过60秒",Toast.LENGTH_SHORT).show();
                        edi_redinformation.setText("");
                    }
                }else {
                    Toast.makeText(this,"声音长度不能为空",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.LL_time:
                new DateTimePickerDialog(WxChatAddVoice.this,System.currentTimeMillis(),2,tv_time1).show();
                break;
            case R.id.rbtn_my:
                typepictires=9;
                Logger.i("类型"+typepictires);
                break;
            case R.id.rbtn_other:
                typepictires=10;
                Logger.i("类型"+typepictires);
                break;
        }
        Logger.i("type"+type);

    }
}
