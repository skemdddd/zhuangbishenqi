package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 * Created by Administrator on 2016/9/21.
 */

public class ZfbTransferShow extends BaseActivity {
    TextView edt_transfer_amount;
    TextView tv_reason;
    TextView tv_transferTime;
    TextView tv_name;
    ImageView img_head;
    TextView tv_account;
    RelativeLayout RL_detail;
    TextView tv_detailmony;
    TextView number;
    ImageButton image_back;
    @Override
    public void initWidget() {
        setContentView(R.layout.zfb_transfer_show);
        findId();
        Intent intent = getIntent();
        if(intent.getStringExtra("typee").equals("1")){
            edt_transfer_amount.setText("+"+ Name.getStringNum(intent.getStringExtra("amount")));
        }else{
            edt_transfer_amount.setText("-"+ Name.getStringNum(intent.getStringExtra("amount")));
            RL_detail.setVisibility(View.VISIBLE);
            tv_detailmony.setText(intent.getStringExtra("detailmony"));
            number.setText(Name.getStringNum(intent.getStringExtra("amount")));

        }

        tv_reason.setText(intent.getStringExtra("reason"));
        tv_transferTime.setText(intent.getStringExtra("transferTime"));
        tv_name.setText(intent.getStringExtra("name"));
        img_head.setImageBitmap(MyBitmapStore.getBmp());
        tv_account.setText(intent.getStringExtra("name")+" "+intent.getStringExtra("qq"));
        image_back.setOnClickListener(this);




        }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
        }
    }

    @Override
    public void findId() {
        edt_transfer_amount= (TextView) findViewById(R.id.edt_transfer_amount);
        tv_reason= (TextView) findViewById(R.id.tv_reason);
        tv_transferTime= (TextView) findViewById(R.id.tv_transferTime);
        tv_name= (TextView) findViewById(R.id.tv_name);
        img_head= (ImageView) findViewById(R.id.img_head);
        tv_account= (TextView) findViewById(R.id.tv_account);
        RL_detail= (RelativeLayout) findViewById(R.id.RL_detail);
        tv_detailmony= (TextView) findViewById(R.id.tv_detailmony);
        number= (TextView) findViewById(R.id.number);
        image_back= (ImageButton) findViewById(R.id.image_back);



    }

    public String  getAccount(String text){

        if(!TextUtils.isEmpty(text) && text.length() > 6 ) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
              return sb.toString();
            }

        }
        return "456";
    }
}
