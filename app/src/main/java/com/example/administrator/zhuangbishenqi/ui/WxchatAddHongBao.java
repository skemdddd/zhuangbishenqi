package com.example.administrator.zhuangbishenqi.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.widget.DateTimePickerDialog;
import com.orhanobut.logger.Logger;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/8/27.
 */

public class WxchatAddHongBao extends WxchatAddPictures implements RadioButton.OnCheckedChangeListener{
    int typepictires=5;
    String typered="1";
    EditText edi_redinformation;
    CheckBox cbx_pull_down;
    TextView tv_title;
    TextView tv_envelope;
    public void initWidget() {
        setContentView(R.layout.wx_chat_hongbao);
        findId();
        typepictires=5;
        typered="2";
        imgbtn_break.setOnClickListener(this);
        LL_time.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        rbtn_other.setOnClickListener(this);
        rbtn_my.setOnClickListener(this);
        cbx_pull_down.setOnCheckedChangeListener(this);

    }



    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.btn_ture:
                HashMap<String,String> map=new HashMap<>();
                if (typepictires == 5) {
                    SharedPreferences preference = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
                    map.put("yourname",preference.getString("myName","苏娜"));
                }else{
                    SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
                    map.put("yourname",preference.getString("myName","小雅"));
                }


                if(!edi_redinformation.getText().toString().equals("")) {
                    map.put("redinformation", edi_redinformation.getText().toString());
                }else{
                    map.put("redinformation", "恭喜发财，大吉大利！");
                }
                map.put("rebtype",typered);
                if(typepictires==5){
                    map.put("type","type5");
                }else{
                    map.put("type","type6");
                }
                map.put("typered",typered);
                map.put("time",tv_time1.getText().toString());
                WxChat.mData.add(map);
                Logger.i("1111111"+WxChat.mData.size());
                finish();
                break;
            case R.id.LL_time:
                new DateTimePickerDialog(this,System.currentTimeMillis(),2,tv_time1).show();
                break;
            case R.id.rbtn_my:
                typepictires=5;
                Logger.i("类型"+typepictires);
                break;
            case R.id.rbtn_other:
                typepictires=6;
                Logger.i("类型"+typepictires);
                break;



        }
        Logger.i("type"+type);

    }

    @Override
    public void findId() {
        imgbtn_break= (ImageButton) findViewById(R.id.img_break);
        btn_true= (Button) findViewById(R.id.btn_ture);
        LL_time= (LinearLayout) findViewById(R.id.LL_time);
        tv_time1= (TextView) findViewById(R.id.tv_time);
        rbtn_my= (RadioButton) findViewById(R.id.rbtn_my);
        rbtn_other= (RadioButton) findViewById(R.id.rbtn_other);
        edi_redinformation= (EditText) findViewById(R.id.edi_redinformation);
        cbx_pull_down= (CheckBox)findViewById(R.id.cbx_pull_down);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_envelope= (TextView) findViewById(R.id.tv_envelope);




    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b){
            typered="1";
        }else{
            typered="2";
        }
        Logger.i("typered"+typered);
    }
}
