package com.example.administrator.zhuangbishenqi.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.zhuangbishenqi.R;
import com.example.administrator.zhuangbishenqi.base.BaseActivity;
import com.example.administrator.zhuangbishenqi.entity.MyBitmapStore;
import com.example.administrator.zhuangbishenqi.entity.Name;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/7/21.
 *
 * 随机头像页面
 */
public class WxZfbChangeNameHeard extends BaseActivity implements View.OnTouchListener{

    ImageButton ibtnBack;
    LinearLayout LLavatar;
    LinearLayout LLname;
    TextView  tv_name;
    ImageView img_heard;
    Button btnRandomName;
    Button btnRandomHeard;
    Button btn_confirm;
    Bitmap photo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initWidget() {
        setContentView(R.layout.activity_wxnchangenameheard);
        findId();
        img_heard.setImageBitmap(MyBitmapStore.getBmp());
        ibtnBack.setOnClickListener(this);
        LLname.setOnTouchListener(this);
        LLavatar.setOnTouchListener(this);
        LLavatar.setOnClickListener(this);
        LLname.setOnClickListener(this);
        btnRandomName.setOnClickListener(this);
        btnRandomHeard.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        setName();

    }

    @Override
    public void widgetClick(View v) {
        Name name= new Name();
        switch (v.getId()){
            case R.id.ibtn_break:
                finish();
                break;
//            确定
            case R.id.btn_confirm:
                Intent intent = new Intent();
                intent.putExtra("finishtext",tv_name.getText().toString());
                Bitmap image = ((BitmapDrawable)img_heard.getDrawable()).getBitmap();
                MyBitmapStore.setBmp(image);
                setResult(2,intent);
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

    @Override
    public void findId() {
        ibtnBack= (ImageButton) findViewById(R.id.ibtn_break);
        LLavatar= (LinearLayout) findViewById(R.id.avatar);
        LLname= (LinearLayout) findViewById(R.id.name);
        tv_name= (TextView) findViewById(R.id.tv_name);
        img_heard= (ImageView) findViewById(R.id.img_head);
        btnRandomName= (Button) findViewById(R.id.randomname);
        btnRandomHeard= (Button) findViewById(R.id.randomheard);
        btn_confirm= (Button) findViewById(R.id.btn_confirm);


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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(data != null) {
                    startPhotoZoom(data.getData());
                }

                break;
            case 2:
                if(data != null)
                {
                   setPicToView(data);
                }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);
    }
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            photo = extras.getParcelable("data");
            img_heard.setImageBitmap(photo);
        }
    }


    public void setName() {
        Intent intent=getIntent();
     tv_name.setText(intent.getStringExtra("name"));
    }
}
