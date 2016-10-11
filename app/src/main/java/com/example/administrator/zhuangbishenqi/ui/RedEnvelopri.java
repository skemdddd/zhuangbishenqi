package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RedEnvelopri extends BaseActivity {
    Button btnexit;
    Button btnquit;
    TextView tv_title;
    ImageButton ibtn_wxred_packet;//微信红包
    ImageButton ibtn_red_zfb_red;//支付宝红包
    ImageButton ibtn_red_qq_red;//qq红包


    @Override
    public void finish()
    {
        super.finish();
        this.overridePendingTransition(R.anim.activity_close,0);
    }
     @Override
    public void initWidget() {
        setContentView(R.layout.redenvelopri);
        findId();
        btnexit.setOnClickListener(this);
        btnquit.setOnClickListener(this);
        ibtn_wxred_packet.setOnClickListener(this);
        ibtn_red_zfb_red.setOnClickListener(this);
        ibtn_red_qq_red.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.exit:
                finish();
                break;
            case R.id.quit:
                finish();
                break;
            case R.id.ibtn_wxred_packet:
                Intent intent = new Intent(this,WxRedInformation.class);
                intent.putExtra("title","微信红包");
                startActivity(intent);
                break;
            case R.id.ibtn_red_zfb_red:
                Intent zfbintent = new Intent(this,ZfbRedBagInformation.class);
                zfbintent.putExtra("title","支付宝红包");
                startActivity(zfbintent);
                break;
            case R.id.ibtn_red_qq_red:
                Intent qqintent= new Intent(this,QqRedInformation.class);
                qqintent.putExtra("title","qq红包");
                startActivity(qqintent);
                break;

        }

    }

    @Override
    public void findId() {
        btnexit= (Button) findViewById(R.id.exit);
        btnquit= (Button) findViewById(R.id.quit);
        ibtn_wxred_packet= (ImageButton) findViewById(R.id.ibtn_wxred_packet);
        ibtn_red_zfb_red= (ImageButton) findViewById(R.id.ibtn_red_zfb_red);
        ibtn_red_qq_red= (ImageButton) findViewById(R.id.ibtn_red_qq_red);
        tv_title= (TextView) findViewById(R.id.title);
    }


}
