package com.example.administrator.zhuangbishenqi.ui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;

import com.example.administrator.zhuangbishenqi.entity.Name;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/8/13.
 */
public class WxSetChatChange extends WxZfbChangeNameHeard {
       Name name;
    public static final String  path=android.os.Environment.getExternalStorageDirectory().getPath() + "/Head/";

    public void setName() {
        name= new Name();
        File dirFile = new File(WxSetChatChange.path);
        if(dirFile.exists()){
            Bitmap mybt = BitmapFactory.decodeFile(path+"myHead.jpg");
            img_heard.setImageBitmap(mybt);
        }else {
            name.setImag(img_heard,"img_10005");
        }
        SharedPreferences preference = getSharedPreferences("lock", Context.MODE_PRIVATE);
        tv_name.setText(preference.getString("myName","小雅"));


    }
    public void widgetClick(View v) {


            switch (v.getId()){
                case R.id.ibtn_break:
                    finish();
                    break;
//            确定
                case R.id.btn_confirm:
                    Bitmap mymage = ((BitmapDrawable)img_heard.getDrawable()).getBitmap();
                    setPicToView(mymage);
                    setSaveName(tv_name);
                    finish();
                    break;
//            头像选择
                case R.id.avatar:
                    Intent i = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//调用android的图库
                    startActivityForResult(i,1);

                    break;
                case R.id.name:
//                自定一名字
                    final EditText editText =new EditText(this);
                    new AlertDialog
                            .Builder(this)
                            .setTitle("填写角色昵称")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setView(editText)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tv_name.setText(editText.getText());

                                }
                            })
                            .setNegativeButton("取消",null)
                            .show();
                    break;
                case R.id.randomname:
                    name.changeName(tv_name);
                    break;
                case R.id.randomheard:
                    name.changeImg(img_heard);

                    break;
            }

        }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case 1:
                    if (data != null) {
                        startPhotoZoom(data.getData());
                    }

                    break;
                case 2:
                    if (data != null ) {
                        Bundle extras = data.getExtras();
                        if(extras !=null){
                            photo = extras.getParcelable("data");
                        }

                        if(photo!=null){
                            setPicToView(photo);//保存在SD卡中
                            img_heard.setImageBitmap(photo);//用ImageView显示出来
                        }
                    }
            }
        }



    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName =path + "myHead.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            Logger.i("路径"+fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void startPhotoZoom(Uri uri) {
        super.startPhotoZoom(uri);
    }
    public void setSaveName(TextView textView){
        String code = textView.getText().toString().trim();
        //步骤2-1：创建一个SharedPreferences.Editor接口对象，lock表示要写入的XML文件名，MODE_WORLD_WRITEABLE写操作
        SharedPreferences.Editor editor = getSharedPreferences("lock", 0x0002).edit();
        //步骤2-2：将获取过来的值放入文件
        editor.putString("myName", code);
        //步骤3：提交
        editor.apply();

    }
}
