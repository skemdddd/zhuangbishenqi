package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 * Created by Administrator on 2016/9/14.
 */

public class WxTransferAccointsShow extends BaseActivity {
    TextView tv_mony;
    TextView monyTime;
    TextView textColour;
    ImageButton image_back;
    TextView tv_transfer_time;
    TextView tv_name;
    Button btn_ture;

    @Override
    public void initWidget() {
        Intent intent = getIntent();

        if(intent.getStringExtra("type").equals("1")){
//            已领取
            setContentView(R.layout.wx_wxtransfer_accoints_show_get);
            findId();
            tv_mony.setText("￥"+ Name.getStringNum(intent.getStringExtra("transfer")));
            monyTime.setText("转账时间："+intent.getStringExtra("tv_money_time"));
            tv_transfer_time.setText("收钱时间："+intent.getStringExtra("tv_transferTime"));
            if (intent.getStringExtra("typee").equals("2")){
                tv_name.setText(intent.getStringExtra("name")+"已收钱");
            }

        }else{
//            未领取
            setContentView(R.layout.wx_wxtransfer_accoints_show);
            findId();
            SpannableStringBuilder builder = new SpannableStringBuilder(textColour.getText().toString());
            ForegroundColorSpan greenSpan = new ForegroundColorSpan(this.getResources().getColor(R.color.fenduan));
            builder.setSpan(greenSpan, 14, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textColour.setText(builder);
            tv_mony.setText("￥"+ Name.getStringNum(intent.getStringExtra("transfer")));
            monyTime.setText("转账时间："+intent.getStringExtra("tv_money_time"));
            if (intent.getStringExtra("typee").equals("2")){
                String a = "一天内朋友未确认，将退还给你。重发转账消息";
                SpannableStringBuilder builder1 = new SpannableStringBuilder(a);
                ForegroundColorSpan greenSpan1 = new ForegroundColorSpan(this.getResources().getColor(R.color.fenduan));
                builder1.setSpan(greenSpan1, a.length()-7, a.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_name.setText("待"+intent.getStringExtra("name")+"确认收钱");
                btn_ture.setVisibility(View.GONE);
                textColour.setText(builder1);

            }



        }

        image_back.setOnClickListener(this);
//   #6f8c5


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
        tv_mony= (TextView) findViewById(R.id.tv_mony);
        monyTime= (TextView) findViewById(R.id.monyTime);
        textColour= (TextView) findViewById(R.id.textColour);
        image_back= (ImageButton) findViewById(R.id.image_back);
        tv_transfer_time= (TextView) findViewById(R.id.tv_transfer_time);
        tv_name= (TextView) findViewById(R.id.tv_name);
        btn_ture= (Button) findViewById(R.id.btn_ture);
    }
}
