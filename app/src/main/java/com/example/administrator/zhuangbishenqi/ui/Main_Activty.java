package com.example.administrator.zhuangbishenqi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.mediav.ads.sdk.adcore.Mvad;
import com.mediav.ads.sdk.interfaces.IMvBannerAd;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by Administrator on 2016/7/15.
 *
 * 主页面
 *
 *
 */
public class Main_Activty  extends BaseActivity{
    ImageButton red_envelopeibtn;//微信红包
    ImageButton wx_chat;
    ImageButton btn_delete;
    ImageButton btn_artifact_transfer_accounts;
    ImageButton btn_artifact_star;
    ImageButton btn_artifact_kuso;
    private IMvBannerAd bannerad = null;
    private RelativeLayout adContainer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initWidget() {
        setContentView(R.layout.main_activity);
        findId();
        red_envelopeibtn.setOnClickListener(this);
        wx_chat.setOnClickListener(this);
        btn_artifact_kuso.setOnClickListener(this);
        btn_artifact_transfer_accounts.setOnClickListener(this);
        btn_artifact_star.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        final String adSpaceid = "aFub09x2i4";
        bannerad = Mvad.showBanner(adContainer, Main_Activty.this, adSpaceid, false);
        bannerad.showAds(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.btn_artifact_red://微信红包
                Intent intent = new Intent(this,RedEnvelopri.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.activity_open,0);
                break;
            case R.id.btn_artifact_wechat_dialogue:
                Intent intent1 = new Intent(this,WxChat.class);
                startActivity(intent1);
                break;
            case R.id.btn_delete:

                if (Environment.getExternalStorageState().equals("mounted")) {
//获取SD卡路径
                    File file = new File(WxSetChatChange.path);

                    if (file.exists()) {//如果路径存在

                        if (file.isDirectory()) {//如果是文件夹
                            File[] childFiles = file.listFiles();//获取文件夹下所有文件
                            if (childFiles == null || childFiles.length == 0) {//如果为空文件夹
                                file.delete();//删除文件夹
                                return;
                            }
                            for (int i = 0; i < childFiles.length; i++) {//删除文件夹下所有文件
                                childFiles[i].delete();
                            }
                            file.delete();//删除文件夹
                        }
                    }
                }
                Logger.i("删除成功");
                break;
//            其他转账
            case R.id.btn_artifact_transfer_accounts:
                Intent intent2= new Intent(this,OtherScreenshots.class);
                startActivity(intent2);
                this.overridePendingTransition(R.anim.activity_open,0);
                break;

            case R.id.btn_artifact_kuso:
                Intent intent3 = new Intent(this,TransferAccounts.class);
                startActivity(intent3);
                this.overridePendingTransition(R.anim.activity_open,0);
                break;



        }
    }

    @Override
    public void findId() {
        red_envelopeibtn= (ImageButton) findViewById(R.id.btn_artifact_red);
        wx_chat= (ImageButton) findViewById(R.id.btn_artifact_wechat_dialogue);
        btn_artifact_transfer_accounts= (ImageButton) findViewById(R.id.btn_artifact_transfer_accounts);
        btn_artifact_star= (ImageButton) findViewById(R.id.btn_artifact_star);
        btn_artifact_kuso= (ImageButton) findViewById(R.id.btn_artifact_kuso);
        btn_delete= (ImageButton) findViewById(R.id.btn_delete);
        adContainer= (RelativeLayout) findViewById(R.id.banner_adcontainer);

    }
}


