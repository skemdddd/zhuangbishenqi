package com.example.administrator.zhuangbishenqi.ui;


import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.adapter.EmojiAdapter;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.widget.DateTimePickerDialog;
import com.example.administrator.zhuangbishenqi.widget.MyRecyclerView;
import com.example.administrator.zhuangbishenqi.widget.SmiliesEditText;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/8/5.
 *
 */
public class AddTextMessage extends BaseActivity{

    MyRecyclerView mRecyclerView;
    SmiliesEditText edi_content;
    ImageButton imgbtn_break;
    Button btn_true;
    LinearLayout LL_time;
    TextView tv_time1;
    RadioButton rbtn_my;
    RadioButton rbtn_other;
    int type=1;
    public HashMap<String,String > map;




    @Override
    public void initWidget() {
        map= new HashMap<>();
        setContentView(R.layout.add_text_message_activity);
        findId();
        type=1;
        imgbtn_break.setOnClickListener(this);
        LL_time.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        rbtn_other.setOnClickListener(this);
        rbtn_my.setOnClickListener(this);
        mRecyclerView.setHasFixedSize(true);
        EmojiAdapter emojiAdapter = new EmojiAdapter(this);
        emojiAdapter.setOnItemClickLitener(new EmojiAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position, ArrayList<String> mDatas) {
                edi_content.insertIcon2(position,edi_content);
            }

        });
        mRecyclerView.setPageSize(7,5);
        mRecyclerView.setAdapter(emojiAdapter);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
               finish();
                break;
            case R.id.btn_ture:
                if(edi_content.getText().toString().equals("")){
                    Toast.makeText(AddTextMessage.this,"抱歉聊天内容不能为空",Toast.LENGTH_SHORT).show();

                }else {
                    map.put("text",edi_content.getText().toString());
                    if(type==1){
                        map.put("image",WxSetChatChange.path+"myHead.jpg");
                        map.put("type","type1");
                    }else{
                        map.put("image",WxSetChatChange.path+"yourHead.jpg");
                        map.put("type","type2");
                    }
                    map.put("bg",WxSetChatChange.path+"bg.jpg");
                    map.put("time",tv_time1.getText().toString());
                    WxChat.mData.add(map);

                  finish();

                }
                break;
            case R.id.LL_time:
                new DateTimePickerDialog(this,System.currentTimeMillis(),2,tv_time1).show();
                break;
            case R.id.rbtn_my:
                type=1;
                break;
            case R.id.rbtn_other:
                type=2;
                break;
        }
        Logger.i("type"+type);

    }

    @Override
    public void findId() {
        mRecyclerView= (MyRecyclerView) findViewById(R.id.rev_emoji);
        edi_content= (SmiliesEditText) findViewById(R.id.edi_content);
        imgbtn_break= (ImageButton) findViewById(R.id.img_break);
        btn_true= (Button) findViewById(R.id.btn_ture);
        LL_time= (LinearLayout) findViewById(R.id.LL_time);
        tv_time1= (TextView) findViewById(R.id.tv_time);
        rbtn_my= (RadioButton) findViewById(R.id.rbtn_my);
        rbtn_other= (RadioButton) findViewById(R.id.rbtn_other);

    }



}
