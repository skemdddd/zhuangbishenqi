package com.example.administrator.zhuangbishenqi.widget;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.ui.AddTextMessage;
import com.example.administrator.zhuangbishenqi.ui.WxChatAddTransfer;
import com.example.administrator.zhuangbishenqi.ui.WxChatAddVoice;
import com.example.administrator.zhuangbishenqi.ui.WxchatAddHongBao;
import com.example.administrator.zhuangbishenqi.ui.WxchatAddPictures;


/**
 * Created by Administrator on 2016/8/5.
 */
public class WxchatDialog extends AlertDialog implements View.OnClickListener{


    public WxchatDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.wx_chat_dialog, null);
        ImageButton ibtn_text = (ImageButton) view.findViewById(R.id.ibtn_text);
        ImageButton ibtn_hongbao = (ImageButton) view.findViewById(R.id.ibtn_hongbao);
        ImageButton ibtn_picture = (ImageButton) view.findViewById(R.id.ibtn_picture);
        ImageButton ibtn_voic = (ImageButton) view.findViewById(R.id.ibtn_voic);
        ImageButton ibtn_transfer = (ImageButton) view.findViewById(R.id.ibtn_transfer);

        ibtn_voic.setOnClickListener(this);
        ibtn_picture.setOnClickListener(this);
        ibtn_text.setOnClickListener(this);
        ibtn_hongbao.setOnClickListener(this);
        ibtn_transfer.setOnClickListener(this);
        setTitle("选择增加类型");
        setView(view);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_text:
                Intent intent = new Intent(getContext(),AddTextMessage.class);
                getContext().startActivity(intent);
                this.dismiss();
                break;
            case R.id.ibtn_hongbao:
                Intent intent1 = new Intent(getContext(), WxchatAddHongBao.class);
                getContext().startActivity(intent1);
                this.dismiss();
                break;
            case R.id.ibtn_picture://聊天图片
                Intent intent2 = new Intent(getContext(), WxchatAddPictures.class);
                getContext().startActivity(intent2);
                this.dismiss();
                break;
            case R.id.ibtn_transfer:
                Intent intent3 = new Intent(getContext(), WxChatAddTransfer.class);
                getContext().startActivity(intent3);
                this.dismiss();
                break;
            case R.id.ibtn_voic:
                Intent intent4 = new Intent(getContext(), WxChatAddVoice.class);
                getContext().startActivity(intent4);
                this.dismiss();
                break;
            }
    }


}
