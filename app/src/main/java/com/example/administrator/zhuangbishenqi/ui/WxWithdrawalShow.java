package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/9/10.
 *
 */

public class WxWithdrawalShow extends BaseActivity{
    TextView bancktype;
    TextView tv_money;
    TextView poundage;
    RelativeLayout RL_type;
    @Override
    public void initWidget() {
        setContentView(R.layout.wx_withdrawal_show);
        findId();
        Intent intent= getIntent();
        String  sAgeFormat1= getResources().getString(R.string.bancktyoe);
        String sFinal1 = String.format(sAgeFormat1, intent.getStringExtra("banck"),intent.getStringExtra("cadid"));
        bancktype.setText(sFinal1);
        tv_money.setText("￥"+ Name.getStringNum(intent.getStringExtra("mony")));

        if(intent.getStringExtra("type").equals("1")){
            double mony = Double.parseDouble(intent.getStringExtra("mony")) *0.001;
            DecimalFormat df   =new DecimalFormat("#.##");
            if(Double.parseDouble(intent.getStringExtra("mony"))<8){
                poundage.setText("￥"+0.00);
            }else{
                poundage.setText("￥"+  df.format(Double.parseDouble(Name.getStringNum(String.valueOf(mony)))));
            }
            RL_type.setVisibility(View.VISIBLE);
        }else{

            RL_type.setVisibility(View.GONE);
        }

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void findId() {
        bancktype= (TextView) findViewById(R.id.bancktype);
        tv_money= (TextView) findViewById(R.id.tv_money);
        poundage= (TextView) findViewById(R.id.poundage);
        RL_type= (RelativeLayout) findViewById(R.id.RL_type);
    }
}
