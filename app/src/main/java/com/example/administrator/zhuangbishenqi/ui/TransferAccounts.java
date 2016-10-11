package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.view.View;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.entity.Name;

/**
 * Created by Administrator on 2016/9/13.
 */

public class TransferAccounts extends RedEnvelopri {
    @Override
    public void initWidget() {
        super.initWidget();
        Name name= new Name();
        name.setImagButton(super.ibtn_wxred_packet,"btn_transfer_accounts_wechat_transfer");
        name.setImagButton(super.ibtn_red_zfb_red,"btn_transfer_accounts_qq_transfer");
        name.setImagButton(super.ibtn_red_qq_red,"btn_transfer_accounts_alipay_transfer");
        super.tv_title.setText("选择转账类型");
    }



    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.quit:
                finish();
                break;
//            微信转账
            case R.id.ibtn_wxred_packet:
                Intent intent = new Intent(this, WxTransfer.class);
                intent.putExtra("title", "微信转账");
                startActivity(intent);
                break;
//            支付转账
            case R.id.ibtn_red_zfb_red:
                Intent zfbintent = new Intent(this, ZfbTransfer.class);
                zfbintent.putExtra("title", "支付转账");
                startActivity(zfbintent);
                break;
//            QQ转账
            case R.id.ibtn_red_qq_red:
                Intent qqintent = new Intent(this, QqTransfer.class);
                qqintent.putExtra("title", "QQ转账");
                startActivity(qqintent);
                break;
        }
    }
}
