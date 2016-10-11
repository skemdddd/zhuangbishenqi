package com.example.administrator.zhuangbishenqi.ui;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/7/23.
 *
 * 微信红包
 */
public class WxRedBag extends BaseActivity{
    TextView tv_name;   //微信名字
    ImageButton ibtn_back;   //返回按钮
    TextView  tv_wx_information;//自定义留言
    CircleImageView img_heard;//微信头像
    TextView tv_wx_money;    //红包金额


    @Override
    public void initWidget() {
        setContentView(R.layout.activity_wx_red_bag);
        findId();
        ibtn_back.setOnClickListener(this);
        Intent intent = getIntent();
        tv_name.setText(intent.getStringExtra("name"));
        img_heard.setImageBitmap(MyBitmapStore.getBmp());
        tv_wx_money.setText(Name.getStringNum(intent.getStringExtra("money")));
        if(intent.getStringExtra("information").equals("")) {
            tv_wx_information.setText(R.string.wx_information);
        }else{
            tv_wx_information.setText(intent.getStringExtra("information"));
        }
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.imageButton:
                ibtn_back.setBackgroundColor(ContextCompat.getColor(this,R.color.SystemBarTint));
                finish();
                break;
        }
    }

    @Override
    public void findId() {
        tv_name= (TextView) findViewById(R.id.tvwx_name);
        tv_wx_information= (TextView) findViewById(R.id.tv_wx_information);
        ibtn_back= (ImageButton) findViewById(R.id.imageButton);
        img_heard= (CircleImageView) findViewById(R.id.wx_img_head_circle);
        tv_wx_money= (TextView) findViewById(R.id.tvwx_money);

    }
}
