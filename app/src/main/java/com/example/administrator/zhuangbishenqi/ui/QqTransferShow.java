package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 *
 * Created by Administrator on 2016/9/22.
 *
 */

public class QqTransferShow extends BaseActivity {
    LinearLayout LL_btn;
    TextView tv_balance;
    TextView tv_mony;
    ImageButton image_back;

    @Override
    public void initWidget() {

        Intent intent = getIntent();
        if(intent.getStringExtra("typee").equals("2")){
            setContentView(R.layout.qq_transfer_show_two);
            findId();
            String s= Name.getStringNum(intent.getStringExtra("transfer_status"))+"元";
            Spannable WordtoSpan = new SpannableString(s);
            WordtoSpan.setSpan(new AbsoluteSizeSpan(66),s.length()-1, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_mony.setText(WordtoSpan);
            String sAgeFormat = getResources().getString(R.string.balance);
            String sFinalAge = String.format(sAgeFormat, intent.getStringExtra("name"));
            tv_balance.setText(sFinalAge);

        }else{
            setContentView(R.layout.qq_transfer_show);
            findId();
            String s= Name.getStringNum(intent.getStringExtra("transfer_status"))+"元";
            Spannable WordtoSpan = new SpannableString(s);
            WordtoSpan.setSpan(new AbsoluteSizeSpan(66),s.length()-1, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_mony.setText(WordtoSpan);
        }

        image_back.setOnClickListener(this);


    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            default:
        }

    }

    @Override
    public void findId() {
        LL_btn= (LinearLayout) findViewById(R.id.LL_btn);
        tv_balance= (TextView) findViewById(R.id.tv_balance);
        tv_mony= (TextView) findViewById(R.id.tv_mony);
        image_back= (ImageButton) findViewById(R.id.image_back);

    }
}
