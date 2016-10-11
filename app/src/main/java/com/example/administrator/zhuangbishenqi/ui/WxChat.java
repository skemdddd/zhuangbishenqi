package com.example.administrator.zhuangbishenqi.ui;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.adapter.DialogueAdapter;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.example.administrator.zhuangbishenqi.widget.WxchatDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/3.
 *
 * 微信聊天
 *
 *
 *
 *
 */
public class WxChat extends BaseActivity implements View.OnTouchListener {
    public static ArrayList<Map<String,String>>mData;
    LinearLayout LL_change;//头像名字选择
    Button btn_addSpeack;//增加对话
    Button btn_makePrview;//生成预览
    ImageButton ibtn_break;//返回
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    static DialogueAdapter mDialogueAdapter;
    ImageView iv_my_head_pic;//我的头像
    ImageView iv_you_head_pic;//对方头像
    Bitmap yourbt;
    Bitmap mybt;
    public static final String  path=android.os.Environment.getExternalStorageDirectory().getPath() + "/Head/";




    @Override
    public void initWidget() {
         mData=new ArrayList<>();
         setContentView(R.layout.activity_wxchat);
         findId();
         LL_change.setOnClickListener(this);
         LL_change.setOnTouchListener(this);

         ibtn_break.setOnClickListener(this);
         btn_addSpeack.setOnClickListener(this);
         btn_makePrview.setOnClickListener(this);

         mLayoutManager = new LinearLayoutManager(WxChat.this);
         mRecyclerView.setLayoutManager(mLayoutManager);
//        高度固定
         mRecyclerView.setHasFixedSize(true);
         mDialogueAdapter = new DialogueAdapter(mData,this);
         mRecyclerView.setAdapter(mDialogueAdapter);
//         设置头像
        initView();

     }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        mDialogueAdapter.notifyDataSetChanged();

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.LL_change:// 头像名字
                Intent intent = new Intent(this,WxSetchat.class);
                startActivity(intent);
                break;
            case R.id.btn_wx_make_preview://生成预览
                Intent intent1 =new Intent(this,WxChatPreview.class);
                startActivity(intent1);
                break;
            case R.id.btn_add://增加对话
                new WxchatDialog(WxChat.this).show();
                break;
            case R.id.ibtn_break://返回
                finish();
                break;

        }
    }

    @Override
    public void findId() {
        LL_change= (LinearLayout) findViewById(R.id.LL_change);
        btn_addSpeack= (Button) findViewById(R.id.btn_add);
        btn_makePrview= (Button) findViewById(R.id.btn_wx_make_preview);
        ibtn_break= (ImageButton) findViewById(R.id.ibtn_break);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        iv_my_head_pic= (ImageView) findViewById(R.id.iv_my_head_pic);
        iv_you_head_pic= (ImageView) findViewById(R.id.iv_you_head_pic);
        ibtn_break= (ImageButton) findViewById(R.id.ibtn_break);



    }
    @Override
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



    public void initView(){
        Name name = new Name();
        File dirFile = new File(WxSetChatChange.path);
        if(dirFile.exists()){
             mybt = BitmapFactory.decodeFile(path+"myHead.jpg");
             yourbt = BitmapFactory.decodeFile(path+"yourHead.jpg");
        }

        if(mybt==null){
            name.setImag(iv_my_head_pic,"img_10005");
        }else {
            iv_my_head_pic.setImageBitmap(mybt);
        }
        if(yourbt==null){
            name.setImag(iv_you_head_pic,"img_10005");
        }else {
            iv_you_head_pic.setImageBitmap(yourbt);
        }
        if (yourbt == null && mybt==null){
            name.setImag(iv_my_head_pic,"img_10005");
            name.setImag(iv_you_head_pic,"img_10025");
        }
    }

}
