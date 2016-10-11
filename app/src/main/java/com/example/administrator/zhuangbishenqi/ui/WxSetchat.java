package com.example.administrator.zhuangbishenqi.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.widget.Wxchatbg;

import java.io.File;


/**
 * Created by Administrator on 2016/8/13.
 */
public class WxSetchat extends BaseActivity implements View.OnTouchListener{
    TextView tv_myname;
    TextView tv_yourname;
    public static ImageView img_bg;
    LinearLayout LL_myinformation;
    LinearLayout LL_yourinformation;
    LinearLayout LL_bg;
    ImageButton ibtn_break;
    ImageView img_myhead;
    ImageView img_yourhead;
    Button btn_ture;
    File dirFile;
    Name name2;
    @Override
    public void initWidget() {
        setContentView(R.layout.wx_chat_message);
        findId();
        ibtn_break.setOnClickListener(this);
        LL_bg.setOnTouchListener(this);
        LL_bg.setOnClickListener(this);
        LL_myinformation.setOnTouchListener(this);
        LL_myinformation.setOnClickListener(this);
        LL_yourinformation.setOnTouchListener(this);
        LL_yourinformation.setOnClickListener(this);
        btn_ture.setOnClickListener(this);
         dirFile = new File(WxSetChatChange.path);
        //检测图片是否存在
        name2 = new Name();
        if( BitmapFactory.decodeFile(WxSetChatChange.path+"myHead.jpg")!=null) {
            //读文件
            Bitmap mybt = BitmapFactory.decodeFile(WxSetChatChange.path+"myHead.jpg");
            img_myhead.setImageBitmap(mybt);
        } else{
           name2.setImag(img_myhead,"img_10005");
        }


        SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
        tv_myname.setText(preference.getString("myName","小雅"));
//        你的名字图片
        SharedPreferences preferenceyour = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
        tv_yourname.setText(preferenceyour.getString("myName","苏娜"));

        if(dirFile.exists()) {
            //读文件
            Bitmap yourbt = BitmapFactory.decodeFile(WxSetChatChange.path+"yourHead.jpg");
            img_yourhead.setImageBitmap(yourbt);
        } else{
            name2.setImag(img_yourhead,"img_10025");
        }

        if(BitmapFactory.decodeFile(WxSetChatChange.path+"bg.jpg")!=null) {
            //读文件
            Bitmap mybg = BitmapFactory.decodeFile(WxSetChatChange.path+"bg.jpg");
            img_bg.setImageBitmap(mybg);
        } else{
            name2.setImag(img_bg,"img_10000");
        }

    }

    @Override
    public void widgetClick(View v) {
       switch (v.getId()){
           case R.id.LL_myinformation:
               Intent intent = new Intent(this,WxSetChatChange.class);
               startActivity(intent);
               break;
           case R.id.LL_yourinformation:
               Intent intent1 = new Intent(this,WxSetChatYourChange.class);
               startActivity(intent1);
               break;
           case R.id.ibtn_break:
               finish();
               break;
           case R.id.btn_ture:
               finish();
               break;
           case R.id.LL_bg:
                new Wxchatbg().show(getFragmentManager() ,"EditNameDialog");
               break;
       }
    }

    @Override
    public void findId() {
        tv_myname= (TextView) findViewById(R.id.tv_myname);
        tv_yourname= (TextView) findViewById(R.id.tv_youname);
        img_bg= (ImageView) findViewById(R.id.img_bg);
        ibtn_break= (ImageButton) findViewById(R.id.ibtn_break);

        LL_myinformation= (LinearLayout) findViewById(R.id.LL_myinformation);
        LL_yourinformation= (LinearLayout) findViewById(R.id.LL_yourinformation);
        LL_bg= (LinearLayout) findViewById(R.id.LL_bg);
        img_myhead= (ImageView) findViewById(R.id.img_myhead);
        img_yourhead= (ImageView) findViewById(R.id.img_yourhead);
        btn_ture= (Button) findViewById(R.id.btn_ture);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(dirFile.exists()) {
            //读文件
            Bitmap mybt = BitmapFactory.decodeFile(WxSetChatChange.path+"myHead.jpg");
            img_myhead.setImageBitmap(mybt);
        } else{
            name2.setImag(img_myhead,"img_10005");
        }
        SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
        tv_myname.setText(preference.getString("myName","小雅"));

        SharedPreferences preferenceyour = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
        tv_yourname.setText(preferenceyour.getString("myName","苏娜"));
        if(BitmapFactory.decodeFile(WxSetChatChange.path+"yourHead.jpg") != null) {
            //读文件
            Bitmap yourbt = BitmapFactory.decodeFile(WxSetChatChange.path+"yourHead.jpg");
            img_yourhead.setImageBitmap(yourbt);
        } else{
            name2.setImag(img_yourhead,"img_10025");
        }

        if(dirFile.exists()) {
            //读文件
            Bitmap mybg = BitmapFactory.decodeFile(WxSetChatChange.path+"bg.jpg");
            img_bg.setImageBitmap(mybg);
        } else{
            name2.setImag(img_bg,"img_10000");
        }

    }

    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==event.ACTION_DOWN)
        {
            v.setBackgroundColor(getResources().getColor(R.color.colorGray));
        }
        else if(event.getAction()==event.ACTION_UP)
        {
            v.setBackgroundColor(getResources().getColor(R.color.colorWhiter));
        }
        return false;
    }
}
