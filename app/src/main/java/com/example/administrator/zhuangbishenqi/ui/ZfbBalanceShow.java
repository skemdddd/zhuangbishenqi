package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 *
 * Created by Administrator on 2016/9/8.
 *
 *
 */

public class ZfbBalanceShow extends BaseActivity {
    ImageButton image_back;
    TextView tv_money;
    @Override
    public void initWidget() {
        setContentView(R.layout.zfb_change);
        findId();
        Intent intent = getIntent();
        image_back.setOnClickListener(this);
        tv_money.setText(Name.getStringNum(intent.getStringExtra("mony")));
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
    }
}
