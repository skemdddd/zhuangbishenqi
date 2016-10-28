package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.mediav.ads.sdk.adcore.Mvad;
import com.mediav.ads.sdk.interfaces.IMvBannerAd;

/**
 * Created by Administrator on 2016/9/8.
 */

public class ZfbBalance extends WxBalance {
    ImageButton img_break;
    EditText edt_money;
    Button btn_make_preview;
    TextView tv_title;
    RelativeLayout adContainer;


    @Override
    public void initWidget() {
        setContentView(R.layout.zfb_balance);
        findId();
        initEditText();
        Intent intent =getIntent();
        tv_title.setText(intent.getStringExtra("title"));
        btn_make_preview.setOnClickListener(this);
        img_break.setOnClickListener(this);
        final String adSpaceid = "aFub09x2i4";
        IMvBannerAd bannerad = Mvad.showBanner(adContainer, this, adSpaceid, false);
        bannerad.showAds(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.make_preview:
                Intent intent = new Intent(this,ZfbBalanceShow.class);
                intent.putExtra("mony",edt_money.getText().toString());
                startActivity(intent);
                break;

        }

    }


    public void initEditText() {
        edt_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    btn_make_preview.setEnabled(true);
                    btn_make_preview.setTextColor(ContextCompat.getColor(ZfbBalance.this,R.color.colorWhiter));
                }else{
                    btn_make_preview.setEnabled(false);
                    btn_make_preview.setTextColor(ContextCompat.getColor(ZfbBalance.this,R.color.colorBack_Gray));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//                保留小数点两位。
                String temp = s.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2)
                {
                    s.delete(posDot + 3, posDot + 4);
                }

            }
        });

    }



    @Override
    public void findId() {
        img_break= (ImageButton) findViewById(R.id.img_break);
        edt_money= (EditText) findViewById(R.id.change_the_amount);
        btn_make_preview= (Button) findViewById(R.id.make_preview);
        tv_title= (TextView) findViewById(R.id.tv_title);
        adContainer= (RelativeLayout) findViewById(R.id.adContainer);

    }






}
