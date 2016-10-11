package com.example.administrator.zhuangbishenqi.ui;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.widget.DateTimePickerDialog;
import com.orhanobut.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;


/**
 * Created by Administrator on 2016/8/5.
 */
public class WxchatAddPictures extends AddTextMessage {

    ImageButton imgbtn_break;
    ImageView imag_pictures;
    Button btn_true;
    LinearLayout LL_time;
    RadioButton rbtn_my;
    TextView tv_time1;
    RadioButton rbtn_other;
    RelativeLayout LL_pictures;
    TextView tv_title;
    int typepictires=3;

    @Override
    public void initWidget() {

        setContentView(R.layout.wx_chat_addpictures);
        typepictires=3;
        Logger.i("类型"+typepictires);
        findId();
        imgbtn_break.setOnClickListener(this);
        LL_time.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        rbtn_other.setOnClickListener(this);
        rbtn_my.setOnClickListener(this);
        LL_pictures.setOnClickListener(this);
        tv_title.setText("增加背景");


    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.img_break:
                finish();
                break;
            case R.id.btn_ture:
                HashMap<String,String> map=new HashMap<>();
                map.put("pictires",WxSetChatChange.path+"pictires.jpg");
                map.put("text","");
                if(typepictires==3){
                    map.put("type","type3");
                }else{
                    map.put("type","type4");
                }
                map.put("time",tv_time1.getText().toString());
                WxChat.mData.add(map);
                Logger.i("1111111"+WxChat.mData.size());
                finish();
                break;
            case R.id.LL_time:
                new DateTimePickerDialog(this,System.currentTimeMillis(),2,tv_time1).show();
                break;
            case R.id.rbtn_my:
                typepictires=3;
                Logger.i("类型"+typepictires);
                break;
            case R.id.rbtn_other:
                typepictires=4;
                Logger.i("类型"+typepictires);
                break;
            case R.id.LL_pictures:
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//调用android的图库
                startActivityForResult(i,1);
                break;
        }
        Logger.i("type"+type);

    }

    @Override
    public void findId() {
        imgbtn_break= (ImageButton) findViewById(R.id.img_break);
        btn_true= (Button) findViewById(R.id.btn_ture);
        LL_time= (LinearLayout) findViewById(R.id.LL_time);
        tv_time1= (TextView) findViewById(R.id.tv_time);
        rbtn_my= (RadioButton) findViewById(R.id.rbtn_my);
        rbtn_other= (RadioButton) findViewById(R.id.rbtn_other);
        LL_pictures= (RelativeLayout) findViewById(R.id.LL_pictures);
        imag_pictures= (ImageView) findViewById(R.id.imag_pictures);
        tv_title= (TextView) findViewById(R.id.tv_title);
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
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case 1:
                ContentResolver resolver = getContentResolver();
                //照片的原始资源地址
                if (data != null) {
                    Uri originalUri = data.getData();
                    try {
                        //使用ContentProvider通过URI获取原始图片
                        Bitmap photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {

                            saveFile(photo,WxChat.path + "pictires.jpg");
                            imag_pictures.setImageBitmap(photo);

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


}
