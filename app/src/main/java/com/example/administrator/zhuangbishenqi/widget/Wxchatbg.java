package com.example.administrator.zhuangbishenqi.widget;

import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.ui.WxSetChatChange;
import com.example.administrator.zhuangbishenqi.ui.WxSetchat;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/8/15.
 */
public class Wxchatbg extends DialogFragment implements View.OnClickListener{
    public static int BGTYPE=1;
    View view;
    ImageButton ibtn_custom;
    ImageButton ibtn_default;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_wxchatbg, container);
        ibtn_custom= (ImageButton) view.findViewById(R.id.ibtn_custom);
        ibtn_default= (ImageButton) view.findViewById(R.id.ibtn_default);
        ibtn_custom.setOnClickListener(this);
        ibtn_default.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_custom :
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//调用android的图库
               startActivityForResult(i,1);
                BGTYPE=1;

                break;
            case R.id.ibtn_default:
                BGTYPE=2;
                Class drawable = R.drawable.class;
                Field field = null;
                try {
                    field = drawable.getField("img_10000");
                    int res_ID= field.getInt(field.getName());
                    Bitmap bitmap = BitmapFactory. decodeResource (view.getContext().getResources(),res_ID);
                    saveFile(bitmap,WxSetChatChange.path + "bg.jpg");
                    WxSetchat.img_bg.setImageBitmap(bitmap);
                    dismiss();
                } catch (Exception e) {
                }
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case 1:
                ContentResolver resolver = getActivity().getContentResolver();
                //照片的原始资源地址
                if (data != null) {
                    Uri originalUri = data.getData();
                    try {
                        //使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            saveFile(photo,WxSetChatChange.path + "bg.jpg");
                            //释放原始图片占用的内存，防止out of memory异常发生
                            photo.recycle();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
         }

        }
    public void saveFile(Bitmap bm, String fileName) throws Exception {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        File dirFile = new File(fileName);
        //检测图片是否存在
        if(dirFile.exists()){
            dirFile.delete();  //删除原图片
        }
        File myCaptureFile = new File(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        //100表示不进行压缩，70表示压缩率为30%
        bm.compress(Bitmap.CompressFormat.JPEG,100, bos);
        bos.flush();
        bos.close();
    }





}
