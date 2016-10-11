package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 *
 * Created by Administrator on 2016/9/6.
 *
 */

public class OtherScreenshots extends  RedEnvelopri {
    @Override
    public void initWidget() {
        super.initWidget();
        Name name= new Name();
        name.setImagButton(super.ibtn_wxred_packet,"wechat_change");
        name.setImagButton(super.ibtn_red_zfb_red,"wechat_cash");
        name.setImagButton(super.ibtn_red_qq_red,"alipay_balance");
        super.tv_title.setText("选择截图类型");
}


    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.quit:
                finish();
                break;
//            微信零钱
            case R.id.ibtn_wxred_packet:
                Intent intent = new Intent(this, WxBalance.class);
                intent.putExtra("title", "微信零钱");
                startActivity(intent);
                break;
//            微信体现
            case R.id.ibtn_red_zfb_red:
                Intent zfbintent = new Intent(this, WxWithdrawal.class);
                zfbintent.putExtra("title", "微信提现");
                startActivity(zfbintent);
                break;
//            支付宝余额
            case R.id.ibtn_red_qq_red:
                Intent qqintent = new Intent(this, ZfbBalance.class);
                qqintent.putExtra("title", "支付宝余额");
                startActivity(qqintent);
                break;
        }
    }
}
