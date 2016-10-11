package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 * Created by Administrator on 2016/9/8.
 */

public class WxBalanceShow extends BaseActivity{
    ImageButton image_back;
    TextView tv_money;
    LinearLayout LL_type;
    @Override
    public void initWidget() {
        setContentView(R.layout.wx_change);
        findId();
        image_back.setOnClickListener(this);
        Intent intent = getIntent();
        tv_money.setText("￥" + Name.getStringNum(intent.getStringExtra("mony")));
        if(intent.getStringExtra("type").equals("1")){
            LL_type.setVisibility(View.VISIBLE);
        }else{
            LL_type.setVisibility(View.GONE);
        }
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
        image_back= (ImageButton) findViewById(R.id.image_back);
        tv_money= (TextView) findViewById(R.id.tv_money);
        LL_type= (LinearLayout) findViewById(R.id.LL_type);
    }
}
