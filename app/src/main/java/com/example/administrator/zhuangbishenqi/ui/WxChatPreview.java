package com.example.administrator.zhuangbishenqi.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.adapter.WxChatAdapter;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.widget.Wxchatbg;
import com.orhanobut.logger.Logger;


/**
 * Created by Administrator on 2016/8/17.
 */
public class WxChatPreview extends BaseActivity {
    private RecyclerView mRecyclerView;
    WxChatAdapter wxChatAdapter;
    TextView tv_title;
    int hight;
    int width;
    ImageButton image_back;

    @Override
    public void initWidget() {
        setContentView(R.layout.wx_chat_according);
        findId();
        SharedPreferences preference = getSharedPreferences("lockyour", Context.MODE_PRIVATE);
        tv_title.setText(preference.getString("myName","小雅"));


        if(Wxchatbg.BGTYPE == 1 ){
            ViewTreeObserver vto = mRecyclerView.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                   try {
                       mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                       hight=mRecyclerView.getHeight();
                       width=mRecyclerView.getWidth();
                       Logger.i("长款"+mRecyclerView.getHeight()+mRecyclerView.getWidth());
                       Bitmap bgbt = BitmapFactory.decodeFile(WxSetChatChange.path+"bg.jpg");

                       Drawable drawable =new BitmapDrawable(getResources(),zoomImg
                               (bgbt,mRecyclerView.getWidth(),mRecyclerView.getHeight()));
                       mRecyclerView.setBackground(drawable);
                   }catch (Exception e){
                       mRecyclerView.setBackgroundColor(Color.parseColor("#EBEBEB"));
                   }
                }
            });

        }else{
            mRecyclerView.setBackgroundColor(Color.parseColor("#EBEBEB"));
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        高度固定
        mRecyclerView.setHasFixedSize(false);
        wxChatAdapter = new WxChatAdapter(WxChat.mData, this);
        mRecyclerView.setAdapter(wxChatAdapter);
        image_back.setOnClickListener(this);
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
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView_chat);
        tv_title= (TextView) findViewById(R.id.tv_title);
        image_back= (ImageButton) findViewById(R.id.image_back);

    }
    public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight){

        // 获得图片的宽高

        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }


}
